import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.Movie;
import Commands.*;
import Manager.ElementInputManager;
import Response.CommandResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        String[] ObjectCommands = {"add", "update", "add_if_max", "add_if_min"};
        String[] Commands = {"help", "info", "show", "add", "update", "remove_by_id", "clear", "execute_script", "exit", "add_if_max", "add_if_min", "history", "remove_any_by_usa_box_office", "count_less_than_screenwriter", "print_field_ascending_mpaa_rating"};
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("help", new HelpCommand());
        commandMap.put("info", new InfoCommand());
        commandMap.put("show", new ShowCommand());
        commandMap.put("add", new AddCommand());
        commandMap.put("update", new UpdateIdCommand());
        commandMap.put("remove_by_id", new RemoveByIdCommand());
        commandMap.put("clear", new ClearCommand());
        commandMap.put("save", new SaveCommand());
        commandMap.put("exit", new ExitCommand());
        commandMap.put("history", new HistoryCommand());
        commandMap.put("add_if_max", new AddIfMaxCommand());
        commandMap.put("add_if_min", new AddIfMinCommand());
        commandMap.put("print_field_ascending_mpaa_rating", new PrintFieldAscendingMpaaRatingCommand());
        commandMap.put("remove_any_by_usa_box_office", new RemoveAnyByUsaBoxOfficeCommand());
        commandMap.put("count_less_than_screenwriter", new CountLessThanScreenwriterCommand());
        commandMap.put("execute_script", new ExecuteScriptCommand());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                SocketChannel channel = SocketChannel.open();
                channel.connect(new InetSocketAddress("localhost", 13377));
                System.out.println("Connected to server!");
                Gson mapper = new GsonBuilder()
                        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                        .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
                        .excludeFieldsWithoutExposeAnnotation()
                        .create();

                while (true)
                    {
                        if (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            String[] tokens = (line.split(" "));
                            String commandType = tokens[0];
                            if (Arrays.asList(Commands).contains(commandType)) {
                                Movie movie = Arrays.asList(ObjectCommands).contains(commandType) ? ElementInputManager.getMovie() : null;
                                String arg = tokens.length > 1 ? tokens[1] : null;
                                CommandResponse cmd = new CommandResponse(commandType, arg, movie);
                                String json = mapper.toJson(cmd) + "\n";
                                ByteBuffer buffer = ByteBuffer.wrap(json.getBytes(StandardCharsets.UTF_8));
                                channel.write(buffer);
                                ByteBuffer ReturnBuffer = ByteBuffer.allocate(1024);
                                int bytesRead = channel.read(ReturnBuffer);
                                System.out.println(mapper.fromJson(String.valueOf(ReturnBuffer), String.class));
                                if (commandType.equalsIgnoreCase("exit")){
                                    channel.close();
                                    System.exit(0);
                                }

                            }
                            else {
                                System.out.println("Incorrect command!");
                            }
                        }
                    }
                } catch (IOException e) {
                System.out.println("Server is down! Retrying in 5 sec..");
                Thread.sleep(5000);
            }
        }
    }
}
