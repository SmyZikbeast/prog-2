import Adapters.LocalDateAdapter;
import Adapters.LocalDateTimeAdapter;
import BaseFiles.Movie;
import Commands.*;
import Manager.CollectionManager;
import Response.Request;
import Response.Response;
import Utility.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import postgres.Connector;
import postgres.DBInteractor;
import ui.ServerFrame;
import Response.PacketType;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

/**
 * main server class
 *
 *
 *
 */
public class Server {

    private static volatile int userCount = 0;
    private final int PORT = 13377;
    private static volatile boolean running = true;
    private final DBInteractor interactor;
    private final CollectionManager cm;
    private final Map<String, Command> commandMap;
    private final ForkJoinPool readPool = new ForkJoinPool();
    private final ForkJoinPool processPool = new ForkJoinPool();
    private final ForkJoinPool writePool = new ForkJoinPool();
    private ServerSocket server;
    private ServerFrame serverFrame = new ServerFrame();
    private volatile HashSet userSet = new HashSet<String>();
    private final Gson mapper = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public Server() throws SQLException {
        Connector connector = new Connector();
        Connection con = connector.connect();
        interactor = new DBInteractor(con);
        interactor.initialize();
        CollectionManager cm = new CollectionManager(interactor);
        serverFrame.FTsetCm(cm);
        cm.load();
        this.cm = cm;
        this.commandMap = initCommands();
    }

    private Map<String, Command> initCommands() {
        Map<String, Command> map = new HashMap<>();
        map.put("help", new HelpCommand(cm));
        map.put("info", new InfoCommand(cm));
        map.put("show", new ShowCommand(cm));
        map.put("add", new AddCommand(cm));
        map.put("update", new UpdateIdCommand(cm));
        map.put("remove", new RemoveByIdCommand(cm));
        map.put("clear", new ClearCommand(cm));
        map.put("exit", new ExitCommand(cm));
        map.put("history", new HistoryCommand(cm));
        map.put("add_if_max", new AddIfMaxCommand(cm));
        map.put("add_if_min", new AddIfMinCommand(cm));
        map.put("print_field_ascending_mpaa_rating", new PrintFieldAscendingMpaaRatingCommand(cm));
        map.put("remove_any_by_usa_box_office", new RemoveAnyByUsaBoxOfficeCommand(cm));
        map.put("count_less_than_screenwriter", new CountLessThanScreenwriterCommand(cm));
        map.put("execute_script", new ExecuteScriptCommand(cm));
        map.put("login", new LoginCommand(cm));
        map.put("register", new RegisterCommand(cm));
        map.put("AutoShow", new AutoShow(cm));
        return map;
    }

    public void start() throws IOException {
        server = new ServerSocket(PORT);
        serverFrame.FTsetCm(cm);
        autoRefreshUserSet();
        autoRefreshUserCount();
        while (running) {
            try {
                System.out.println("Waiting for the client request");
                Socket socket = server.accept();
                readPool.execute(() -> {
                    try {
                        HandleClient(socket);
                    } catch (IOException e) {
                        userCount--;
                        System.out.println(e.getMessage());
                    }
                });
            } catch (SocketException e) {
                System.out.println("Client disconnected");
            } catch (NullPointerException e) {
                System.out.println("smth went wrong");
            }
        }
    }
    public void autoRefreshUserCount(){
        new Thread(() -> {
            while (true) {
                serverFrame.update(userCount);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    public void autoRefreshUserSet(){
        new Thread(() -> {
            while (true) {
                userCount = this.userSet.size();
                userSet.clear();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                serverFrame.updateUserSet(new HashSet<String>(userSet));
            }
        }).start();
}
    public static void main(String[] args) throws IOException, SQLException {
        Server server = new Server();
        new Thread(() -> {
            try {
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    String line = console.readLine();
                    if ("exit".equalsIgnoreCase(line)) {
                        System.out.println("Shutting down server..");
                        running = false;
                        System.exit(0);
                    }
                }
            } catch (IOException e) {
                System.out.println("Server stopped");
            }
        }).start();
        server.start();
    }

    void HandleClient(Socket socket) throws IOException {
        System.out.println("client accepted");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        while (running) {
            String message = reader.readLine();
            if (message == null) {
                System.out.println("Client disconnected");
                break;
            }
            Request request = mapper.fromJson(message, Request.class);
            System.out.println(request);
            if (request.getUser()!= null){
                userSet.add(request.getUser().getUsername());
            }
            if (request.getPacketType() != PacketType.PING  && !Objects.equals(request.getType(), "show")){
                System.out.println("request: " + request);
            }
            Object arg = request.getArg();
            Movie movie = null;
            if (arg instanceof LinkedTreeMap<?,?>) {
                movie = mapper.fromJson(
                        mapper.toJson(request.getArg()),
                        Movie.class
                );
            }
            if (movie != null) {
                request.setArg(movie);
            }
            processPool.execute(() -> {
                try {
                    Response response = processRequest(request);
                    writePool.execute(() -> sendResponse(writer, response));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        }
    }
    Response processRequest (Request request) throws SQLException, IOException {
        if (request.getPacketType() == PacketType.COMMAND) {
            String CommandType = request.getType();
            Object CommandArg = request.getArg();
            User CommandUser = request.getUser();
            Command command = commandMap.get(CommandType);
            command.setArg(CommandArg);
            command.setUser(CommandUser);
            return command.execute();
        }
        else {
            Response r = new Response("String", "PONG");
            r.setPacketType(PacketType.PING);
            return r;
        }
    }
    void sendResponse (BufferedWriter writer, Response response){
        try {
            synchronized (writer) {
                writer.write(mapper.toJson(response) + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("Error sending response");
        }
    }
}