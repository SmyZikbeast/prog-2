import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static ServerSocket server;
    private static final int PORT = 13377;

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        server = new ServerSocket(PORT);
        while(true){
            System.out.println("Waiting for the client request");
            Socket socket = server.accept();
            System.out.println("client accepted");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            while(true) {
                Object o = ois.readObject();

            }
        }
    }
}