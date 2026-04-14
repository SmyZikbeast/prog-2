package Manager;

import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.Movie;
import BaseFiles.Person;
import Response.Request;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Scanner;
/**
 * class that runs client on some channel
 *
 *
 *
 */
public class Runner {
    public static int RecursionDepth = 0;
    public final int MAX_RECURSION_DEPTH = 5;
    String[] ObjectCommands = {"add", "add_if_max", "add_if_min"};
    String[] Commands = {"help", "info", "show", "add", "update", "remove_by_id", "clear", "execute_script", "exit", "add_if_max", "add_if_min", "history", "remove_any_by_usa_box_office", "count_less_than_screenwriter", "print_field_ascending_mpaa_rating"};
    Gson mapper = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public void run(Scanner scanner, SocketChannel channel) throws IOException, InterruptedException, NoSuchAlgorithmException {
        System.out.println("user:");
        String username = scanner.nextLine();
        System.out.println("passwd:");
        String password = scanner.nextLine();
        MessageDigest md = MessageDigest.getInstance("SHA-224");
        byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String passwd = new String(hashBytes, StandardCharsets.UTF_8);
        System.out.println(passwd);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");
            String commandType = tokens[0];
            if (Arrays.asList(Commands).contains(commandType)) {
                if ("execute_script".equalsIgnoreCase(commandType)) {
                    RecursionDepth++;
                    if (RecursionDepth > MAX_RECURSION_DEPTH) {
                        System.out.println("max recursion depth exceeded");
                        return;
                    }

                    this.run(new Scanner(new File(tokens[1])), channel);
                    Thread.sleep(100);
                    RecursionDepth--;
                    continue;
                }

                Movie movie = Arrays.asList(ObjectCommands).contains(commandType)
                        ? ElementInputManager.getMovie()
                        : null;

                String arg = tokens.length > 1 ? tokens[1] : null;
                Person person = commandType.equalsIgnoreCase("count_less_than_screenwriter") ? ElementInputManager.getPerson() : null;
                Request cmd = new Request(commandType, arg, movie, person);
                String json = mapper.toJson(cmd) + "\n";
                ByteBuffer buffer = ByteBuffer.wrap(json.getBytes(StandardCharsets.UTF_8));
                channel.write(buffer);
                ByteBuffer ReturnBuffer = ByteBuffer.allocate(8192);

                int bytesRead = channel.read(ReturnBuffer);
                if (bytesRead > 0) {
                    ReturnBuffer.flip();
                    String ResponseString = StandardCharsets.UTF_8.decode(ReturnBuffer).toString();
                    Response response = mapper.fromJson(ResponseString, Response.class);
                    String DataType = response.getDataType();
                    Object Data = response.getData();
                    System.out.println(OutputManager.SerializeValue(DataType, Data));
                    if (OutputManager.SerializeValue(DataType, Data).equalsIgnoreCase("Found Such ID")) {
                        movie = ElementInputManager.getMovie(Integer.valueOf(arg));
                        cmd = new Request(commandType, arg, movie, person);
                        json = mapper.toJson(cmd) + "\n";
                        buffer = ByteBuffer.wrap(json.getBytes(StandardCharsets.UTF_8));
                        channel.write(buffer);
                        ReturnBuffer = ByteBuffer.allocate(1024);
                        bytesRead = channel.read(ReturnBuffer);
                        if (bytesRead > 0) {
                            ReturnBuffer.flip();
                            ResponseString = StandardCharsets.UTF_8.decode(ReturnBuffer).toString();
                            response = mapper.fromJson(ResponseString, Response.class);
                            DataType = response.getDataType();
                            Data = response.getData();
                            System.out.println(OutputManager.SerializeValue(DataType, Data));
                        }
                    }
                }
                else {
                    throw new IOException();
                }
                if (commandType.equalsIgnoreCase("exit")) {
                    channel.close();
                    System.exit(0);
                }
            } else {
                System.out.println("Incorrect command!");
            }
        }
    }
}