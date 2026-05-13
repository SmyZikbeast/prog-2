package ui;

import Service.ClientService;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(ClientService service) throws InterruptedException {
        this.setTitle("My App");
        this.setSize(900, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Список", new FilmList(service));
        tabs.addTab("Просмотр", new FilmRedactor());
        tabs.addTab("Редактор", new FilmView());

        this.add(tabs);
        while (!service.getUserState()){
            Thread.sleep(1000);
        }

        this.setVisible(true);
    }
}
