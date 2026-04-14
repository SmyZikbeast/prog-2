import Manager.Runner;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
/**
 * Main client class
 * <p>
 * Establishes connection between client and server
 *
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                SocketChannel channel = SocketChannel.open();
                channel.connect(new InetSocketAddress("localhost", 13377));
                System.out.println("Connected to server!");
                Runner runner = new Runner();
                runner.run(scanner, channel);
                } catch (IOException e) {
                System.out.println(e.getMessage());
                Thread.sleep(5000);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
