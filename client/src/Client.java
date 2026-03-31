import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.Movie;
import Manager.ElementInputManager;
import Manager.OutputManager;
import Response.CommandResponse;
import Response.Response;
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
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        String[] ObjectCommands = {"add", "update", "add_if_max", "add_if_min"};
        String[] Commands = {"help", "info", "show", "add", "update", "remove_by_id", "clear", "execute_script", "exit", "add_if_max", "add_if_min", "history", "remove_any_by_usa_box_office", "count_less_than_screenwriter", "print_field_ascending_mpaa_rating"};
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
                                if (bytesRead>0){
                                    ReturnBuffer.flip();
                                    String ResponseString = StandardCharsets.UTF_8.decode(ReturnBuffer).toString();
                                    Response response = mapper.fromJson(ResponseString, Response.class);
                                    String DataType = response.getDataType();
                                    Object Data = response.getData();
                                    System.out.println(OutputManager.SerializeValue(DataType, Data));
                                }


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
