import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.Movie;
import BaseFiles.Person;
import Commands.*;
import Manager.CollectionManager;
import Response.Request;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import postgres.Connector;
import postgres.DBInteractor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

/**
 * main server class
 *
 *
 *
 */
public class Server {
    private final int PORT = 13377;
    private static volatile boolean running = true;
    private final DBInteractor interactor;
    private final CollectionManager cm;
    private final Map<String, Command> commandMap;
    private final ForkJoinPool readPool = new ForkJoinPool();
    private final ForkJoinPool processPool = new ForkJoinPool();
    private final ForkJoinPool writePool = new ForkJoinPool();
    private ServerSocket server;
    private final Gson mapper = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public Server(DBInteractor interactor, CollectionManager cm) {
        this.interactor = interactor;
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
        map.put("remove_by_id", new RemoveByIdCommand(cm));
        map.put("clear", new ClearCommand(cm));
        map.put("exit", new ExitCommand(cm));
        map.put("history", new HistoryCommand(cm));
        map.put("add_if_max", new AddIfMaxCommand(cm));
        map.put("add_if_min", new AddIfMinCommand(cm));
        map.put("print_field_ascending_mpaa_rating", new PrintFieldAscendingMpaaRatingCommand(cm));
        map.put("remove_any_by_usa_box_office", new RemoveAnyByUsaBoxOfficeCommand(cm));
        map.put("count_less_than_screenwriter", new CountLessThanScreenwriterCommand(cm));
        map.put("execute_script", new ExecuteScriptCommand(cm));
        return map;
    }

    public void start() throws IOException {
        server = new ServerSocket(PORT);
        while (running) {
            try {
                System.out.println("Waiting for the client request");
                Socket socket = server.accept();
                readPool.execute(() -> {
                    try {
                        HandleClient(socket);
                    } catch (IOException e) {
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

    public static void main(String[] args) throws IOException, SQLException {
        Connector connector = new Connector();
        Connection con = connector.connect();

        DBInteractor interactor = new DBInteractor(con);
        CollectionManager cm = new CollectionManager(interactor);
        cm.load();
        Server server = new Server(interactor, cm);
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
                System.out.println("Received: " + message);
                Request request = mapper.fromJson(message, Request.class);
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
            String CommandType = request.getType().toLowerCase();
            Response response;
            if (CommandType.equalsIgnoreCase("register")) {
                boolean f = interactor.addUser(request.getArg(), Arrays.toString(request.getArg2()));
                response = new Response("String", f ? "Register success" : "Username already taken");
            } else if (CommandType.equalsIgnoreCase("login")) {
                boolean f = interactor.loginUser(request.getArg(), Arrays.toString(request.getArg2()));
                response = new Response("String", f ? "Login success" : "Wrong password or no such user");
            } else {
                String CommandArg = request.getArg();
                Movie CommandMovie = request.getMovie();
                Person CommandPerson = request.getPerson();
                String CommandUser = request.getUser();
                Command command = commandMap.get(CommandType);
                command.setArg(CommandArg);
                command.setMovie(CommandMovie);
                command.setPerson(CommandPerson);
                command.setUser(CommandUser);
                if (CommandType.equalsIgnoreCase("Update") && CommandMovie == null) {
                    Command FindIdCommand = new FindIdCommand(cm);
                    FindIdCommand.setUser(CommandUser);
                    FindIdCommand.setArg(CommandArg);
                    response = FindIdCommand.execute();
                } else {
                    response = command.execute();
                }
                System.out.println("executed command: " + CommandType);
                System.out.println("sending: " + mapper.toJson(response, Response.class));
            }
            return response;
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