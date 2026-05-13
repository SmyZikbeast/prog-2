
import Service.ClientService;
import ui.*;

/**
 * Main client class
 * <p>
 * Establishes connection between client and server
 *
 */

public class Client {
    public static void main(String[] args) throws InterruptedException {
        ClientService service = new ClientService();
        LoginFrame LFrame = new LoginFrame(service);
        while (!service.getUserState()){}
        MainFrame MFrame = new MainFrame(service);
    }
}
