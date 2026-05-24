
import Service.ClientService;
import localization.LocalizationManager;
import localization.RuLang;
import ui.*;

import java.io.IOException;

/**
 * Main client class
 * <p>
 * Establishes connection between client and server
 *
 */

public class Client {
    public static void main(String[] args) throws InterruptedException {
        LocalizationManager lm = new LocalizationManager(new RuLang());
        ClientService service = new ClientService();
        LoginFrame LFrame = new LoginFrame(service, lm);
        while (!service.getUserState()){}
        MainFrame MFrame = new MainFrame(service, lm);
    }
}
