import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            try {
                Socket socket = new Socket("127.0.0.1", 13377);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                System.out.println("Connected to server!");

            } catch (Exception e) {
                System.out.println("Server seems busy or down, retrying in 5 sec...");
                Thread.sleep(5000);
            }
        }
    }
}
