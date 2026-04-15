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
/**
 * main server class
 *
 *
 *
 */
public class Server {
    private static volatile boolean running = true;
    private static ServerSocket server;
    private static final int PORT = 13377;

    public static void main(String[] args) throws IOException, SQLException {
        Connector connector = new Connector();
        Connection con = connector.connect();
        DBInteractor interactor = new DBInteractor(con);
        System.out.println(interactor.getMovies());
        CollectionManager cm = new CollectionManager(interactor);
        cm.load();
        server = new ServerSocket(PORT);
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("help", new HelpCommand(cm));
        commandMap.put("info", new InfoCommand(cm));
        commandMap.put("show", new ShowCommand(cm));
        commandMap.put("add", new AddCommand(cm));
        commandMap.put("update", new UpdateIdCommand(cm));
        commandMap.put("remove_by_id", new RemoveByIdCommand(cm));
        commandMap.put("clear", new ClearCommand(cm));
        commandMap.put("exit", new ExitCommand(cm));
        commandMap.put("history", new HistoryCommand(cm));
        commandMap.put("add_if_max", new AddIfMaxCommand(cm));
        commandMap.put("add_if_min", new AddIfMinCommand(cm));
        commandMap.put("print_field_ascending_mpaa_rating", new PrintFieldAscendingMpaaRatingCommand(cm));
        commandMap.put("remove_any_by_usa_box_office", new RemoveAnyByUsaBoxOfficeCommand(cm));
        commandMap.put("count_less_than_screenwriter", new CountLessThanScreenwriterCommand(cm));
        commandMap.put("execute_script", new ExecuteScriptCommand(cm));
        Gson mapper = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        new Thread(() -> {
            try {
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    String line = console.readLine();
                    if ("exit".equalsIgnoreCase(line)) {
                        System.out.println("Shutting down server..");
                        running = false;
                        server.close();
                        System.exit(0);
                    }
                }
            } catch (IOException e) {
                System.out.println("Server stopped");
            }
        }).start();
        while (running) {
             try {
                System.out.println("Waiting for the client request");
                Socket socket = server.accept();
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
                    System.out.println("Получено: " + message);
                    Request request = mapper.fromJson(message, Request.class);
                    String CommandType = request.getType().toLowerCase();
                    if (CommandType.equalsIgnoreCase("register")) {
                        boolean f = interactor.addUser(request.getArg(), Arrays.toString(request.getArg2()));
                        if (!f){
                            Response response = new Response("String", "Username already taken");
                            writer.write(mapper.toJson(response) + "\n");
                            writer.flush();
                        }
                        else{
                            Response response = new Response("String", "Register success");
                            writer.write(mapper.toJson(response) + "\n");
                            writer.flush();
                        }

                    } else if(CommandType.equalsIgnoreCase("login")){
                        boolean f = interactor.loginUser(request.getArg(), Arrays.toString(request.getArg2()));
                        if (!f){
                            Response response = new Response("String", "Wrong password or no such user");
                            writer.write(mapper.toJson(response) + "\n");
                            writer.flush();
                        }
                        else{
                            Response response = new Response("String", "Login success");
                            writer.write(mapper.toJson(response) + "\n");
                            writer.flush();
                        }
                    }
                    else
                    {
                        String CommandArg = request.getArg();
                        Movie CommandMovie = request.getMovie();
                        Person CommandPerson = request.getPerson();

                        Command command = commandMap.get(CommandType);
                        command.setArg(CommandArg);
                        command.setMovie(CommandMovie);
                        command.setPerson(CommandPerson);
                        Response response;
                        if (CommandType.equalsIgnoreCase("Update") & CommandMovie == null) {
                            Command FindIdCommand = new FindIdCommand(cm);
                            FindIdCommand.setArg(CommandArg);
                            response = FindIdCommand.execute();
                        } else {
                            response = command.execute();
                        }
                        System.out.println("executed command: " + CommandType);
                        System.out.println("sending: " + mapper.toJson(response, Response.class));
                        writer.write(mapper.toJson(response) + "\n");
                        writer.flush();
                    }
                }
             }
             catch (SocketException e) {
                 System.out.println("Client disconnected");
             }
             catch (NullPointerException e) {
                 System.out.println("smth went wrong");
             }
        }
    }
}