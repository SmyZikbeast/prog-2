package ui;

import BaseFiles.Movie;
import Service.ClientService;
import Service.MovieController;
import localization.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Localizable{
    JTabbedPane tabs = new JTabbedPane();
    private FilmList filmList;
    private FilmRedactor filmRedactor;
    private FilmView filmView;
    private MovieController controller;
    private LocalizationManager lm;
    private Console console;
    CommandList commandList;
    public MainFrame(ClientService service, LocalizationManager lm) throws InterruptedException {
        this.lm = lm;
        this.setLayout(new BorderLayout());
        this.setTitle("Client");
        this.setSize(900, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JLabel userLabel = new JLabel(service.getUser().getUsername());
        controller = new MovieController(service,this);
        filmList = new FilmList(service, controller, lm);
        filmRedactor = new FilmRedactor(service, lm);
        filmView = new FilmView(service);
        commandList = new CommandList(service, lm);
        console = new Console(service, lm);
        commandList.setConsole(console);
        JPanel header = new JPanel();
        JComboBox<String> langBox = getStringJComboBox(lm);
        header.add(userLabel);
        header.add(langBox);
        tabs.addTab(lm.getLang().list(), filmList);
        tabs.addTab(lm.getLang().view(), filmView);
        tabs.addTab(lm.getLang().editor(), filmRedactor);
        tabs.addTab(lm.getLang().commands(), commandList);
        tabs.addTab(lm.getLang().console(), console);
        this.add(header, BorderLayout.NORTH);
        this.add(tabs, BorderLayout.CENTER);
        while (!service.getUserState()){
            Thread.sleep(100);
        }

        this.setVisible(true);
        updateAllLanguages();
    }

    private JComboBox<String> getStringJComboBox(LocalizationManager lm) {
        JComboBox<String> langBox = new JComboBox<>(
                new String[]{"RU", "SV", "NO", "ES"}
        );
        langBox.setSelectedItem(lm.getLanguage());
        langBox.addActionListener(e -> {
            String selected = (String) langBox.getSelectedItem();
            switch (selected) {
                case "RU" -> lm.setLang(new RuLang());
                case "SV" -> lm.setLang(new SeLang());
                case "NO" -> lm.setLang(new NoLang());
                case "ES" -> lm.setLang(new EsLang());
            }
            updateAllLanguages();
        });
        return langBox;
    }

    public void openEditor(Movie movie) {
        filmRedactor.setMovie(movie);
        tabs.setSelectedComponent(filmRedactor);
    }
    @Override
    public void updateLanguage(){
        tabs.setTitleAt(0, lm.getLang().list());
        tabs.setTitleAt(1, lm.getLang().view());
        tabs.setTitleAt(2, lm.getLang().editor());
        tabs.setTitleAt(3, lm.getLang().commands());
        tabs.setTitleAt(4, lm.getLang().console());
        repaint();
        revalidate();
    }
    public void updateAllLanguages(){
        this.updateLanguage();
        filmList.updateLanguage();
        filmRedactor.updateLanguage();
        commandList.updateLanguage();
        console.updateLanguage();
    }
}
