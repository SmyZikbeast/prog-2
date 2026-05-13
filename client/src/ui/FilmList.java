package ui;

import Service.ClientService;

import javax.swing.*;

public class FilmList extends JPanel {
    public FilmList(ClientService service){
        JLabel label = new JLabel("Hello, "+ service.getUser().getUsername());
        this.add(label);
    }
}
