import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import Commands.Command;
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

public class Server {

    private static ServerSocket server;
    private static final int PORT = 13377;

    public static void main(String[] args) throws IOException{
        server = new ServerSocket(PORT);
        Gson mapper = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        while (true) {
             try {
                System.out.println("Waiting for the client request");
                Socket socket = server.accept();
                System.out.println("client accepted");
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
                while (true) {
                    String message = reader.readLine();
                    System.out.println("Получено: " + message);
                    CommandResponse commandResponse = mapper.fromJson(message, CommandResponse.class);
                    Command command = commandMap.get(commandResponse.getType().toLowerCase());
                    Response response = command.execute();
                    if (response != null) {
                        writer.write(mapper.toJson(response.getDataType()));
                    }
                }
            }
             catch (SocketException e) {
                 System.out.println("Client disconnected");
             }
        }
    }
}