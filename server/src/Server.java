import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.Movie;
import BaseFiles.Person;
import Commands.*;
import Manager.CollectionManager;
import Response.CommandResponse;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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

    public static void main(String[] args) throws IOException{
        CollectionManager cm = new CollectionManager();
        File collectionFile = new File("File.json");
        server = new ServerSocket(PORT);
        if (!collectionFile.createNewFile()) {
            cm.load("File.json");
        }
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
                        cm.save("File.json");
                        server.close();
                        System.exit(0);
                    }
                    if ("save".equalsIgnoreCase(line)) {
                        System.out.println("saving to file");
                        cm.save("File.json");
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
                        CommandResponse commandResponse = mapper.fromJson(message, CommandResponse.class);
                        String CommandType = commandResponse.getType().toLowerCase();
                        String CommandArg = commandResponse.getArg();
                        Movie CommandMovie = commandResponse.getMovie();
                        Person CommandPerson = commandResponse.getPerson();

                        Command command = commandMap.get(CommandType);
                        command.setArg(CommandArg);
                        command.setMovie(CommandMovie);
                        command.setPerson(CommandPerson);
                        Response response;
                        if (CommandType.equalsIgnoreCase("Update") & CommandMovie == null){
                             Command FindIdCommand = new FindIdCommand(cm);
                             FindIdCommand.setArg(CommandArg);
                             response = FindIdCommand.execute();
                        }
                        else {
                             response = command.execute();
                        }
                        System.out.println("executed command: " + CommandType);
                        System.out.println("sending: " + mapper.toJson(response, Response.class));
                        writer.write(mapper.toJson(response) + "\n");
                        writer.flush();
                    }
             }
             catch (SocketException e) {
                 System.out.println("Client disconnected");
             }
             catch (NullPointerException e) {
                 System.out.println("smth went wrong");;
             }
        }
    }
}