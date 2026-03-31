import java.io.*;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Server {

    private static ServerSocket server;
    private static final int PORT = 13377;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        server = new ServerSocket(PORT);
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
                }
            }
             catch (SocketException e) {
                 System.out.println("Client disconnected");
             }
        }
    }
}