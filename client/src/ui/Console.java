package ui;

import Service.ClientService;
import localization.Localizable;
import localization.LocalizationManager;

import javax.swing.*;
import java.awt.*;

public class Console extends JPanel implements Localizable {
    ClientService service;
    LocalizationManager lm;
    JTextArea console;
    JScrollPane scrollPane;
    public Console(ClientService service, LocalizationManager lm){
        this.service = service;
        this.lm = lm;
        build();
    }
    public void initComponents(){
        setLayout(new BorderLayout());
        console = new JTextArea();
        scrollPane = new JScrollPane(console);
    }
    public void build(){
        initComponents();
        add(scrollPane,BorderLayout.CENTER);
        updateLanguage();
    }
    @Override
    public void updateLanguage() {
        console.setText(lm.getLang().console());
    }
    public void write(String s){
        console.setText(s);
    }
}
