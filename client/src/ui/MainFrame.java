package ui;

import BaseFiles.Movie;
import Service.ClientService;
import Service.MovieController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    JTabbedPane tabs = new JTabbedPane();
    private FilmList filmList;
    private FilmRedactor filmRedactor;
    private FilmView filmView;
    private MovieController controller;
    public MainFrame(ClientService service) throws InterruptedException, IOException {
        this.setLayout(new BorderLayout());
        this.setTitle("My App");
        this.setSize(900, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JLabel userLabel = new JLabel(service.getUser().getUsername());
        controller = new MovieController(service,this);
        filmList = new FilmList(service, controller);
        filmRedactor = new FilmRedactor(service);
        filmView = new FilmView();
        JPanel header = new JPanel();
        header.add(userLabel);
        tabs.addTab("Список", filmList);
        tabs.addTab("Просмотр", filmView);
        tabs.addTab("Редактор", filmRedactor);
        this.add(header, BorderLayout.NORTH);
        this.add(tabs, BorderLayout.CENTER);
        while (!service.getUserState()){
            Thread.sleep(1000);
        }

        this.setVisible(true);
    }
    public void openEditor(Movie movie) {
        filmView.setMovie(movie);
        filmRedactor.setMovie(movie);
        tabs.setSelectedComponent(filmRedactor);
    }
}
