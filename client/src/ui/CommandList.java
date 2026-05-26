package ui;

import Response.Request;
import Service.ClientService;
import localization.Localizable;
import localization.LocalizationManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class CommandList extends JPanel implements Localizable {
    ClientService service;
    LocalizationManager lm;
    Console console;
    String res;
    historyManager historyManager = new historyManager();
    JLabel helpLabel;
    JLabel infoLabel;
    JLabel showLabel;
    JLabel addLabel;
    JLabel updateLabel;
    JLabel removeIdLabel;
    JLabel executeScriptLabel;
    JLabel historyLabel;
    JLabel removeByUSABoxOfficeLabel;
    JLabel addIfMaxLabel;
    JLabel addIfMinLabel;

    JTextField helpField;
    JTextField infoField;
    JTextField showField;
    JTextField addField;
    JTextField updateField;
    JTextField removeIdField;
    JTextField executeScriptField;
    JTextField historyField;
    JTextField removeByUSABoxOfficeField;
    JTextField addIfMaxField;
    JTextField addIfMinField;

    JButton helpButton;
    JButton infoButton;
    JButton showButton;
    JButton addButton;
    JButton updateButton;
    JButton removeIdButton;
    JButton executeScriptButton;
    JButton historyButton;
    JButton removeByUSABoxOfficeButton;
    JButton addIfMaxButton;
    JButton addIfMinButton;

    public CommandList(ClientService service, LocalizationManager lm){
        this.service = service;
        this.lm = lm;
        setLayout(new GridLayout(15, 3));
        build();
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public void initComponents(){
        helpLabel = new JLabel();
        infoLabel = new JLabel();
        showLabel = new JLabel();
        addLabel = new JLabel();
        updateLabel = new JLabel();
        removeIdLabel = new JLabel();
        executeScriptLabel = new JLabel();
        historyLabel = new JLabel();
        removeByUSABoxOfficeLabel = new JLabel();
        addIfMaxLabel = new JLabel();
        addIfMinLabel = new JLabel();

        helpField = new JTextField();
        infoField = new JTextField();
        showField = new JTextField();
        addField = new JTextField();
        updateField = new JTextField();
        removeIdField = new JTextField();
        executeScriptField = new JTextField();
        historyField = new JTextField();
        removeByUSABoxOfficeField = new JTextField();
        addIfMaxField = new JTextField();
        addIfMinField = new JTextField();

        helpButton = new JButton();
        infoButton = new JButton();
        showButton = new JButton();
        addButton = new JButton();
        updateButton = new JButton();
        removeIdButton = new JButton();
        executeScriptButton = new JButton();
        historyButton = new JButton();
        removeByUSABoxOfficeButton = new JButton();
        addIfMaxButton = new JButton();
        addIfMinButton = new JButton();

        helpButton.addActionListener(e -> {
            try {
                res = service.sendRequest(new Request("help", helpField.getText(), service.getUser()));
                console.write(res);
                historyManager.add("help");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        infoButton.addActionListener(e -> {
            try {
                res = service.sendRequest(new Request("info", infoField.getText(), service.getUser()));
                console.write(res);
                historyManager.add("info");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        showButton.addActionListener(e -> {
            try {
                res = service.sendRequest(new Request("show", showField.getText(), service.getUser()));
                console.write(res);
                historyManager.add("show");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        addButton.addActionListener(e -> {
            try {
                res = service.sendRequest(new Request("add", addField.getText(), service.getUser()));
                console.write(res);
                historyManager.add("add");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        updateButton.addActionListener(e -> {
            try {
                res = service.sendRequest(new Request("update", updateField.getText(), service.getUser()));
                console.write(res);
                historyManager.add("update");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        removeIdButton.addActionListener(e -> {
            try {
                res = service.sendRequest(new Request("remove_by_id", removeIdField.getText(), service.getUser()));
                console.write(res);
                historyManager.add("remove_by_id");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        executeScriptButton.addActionListener(e -> {
            try {
                res = service.sendRequest(new Request("execute_script", executeScriptField.getText(), service.getUser()));
                console.write(res);
                historyManager.add("execute_script");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        historyButton.addActionListener(e -> {
            List<String> resList = historyManager.getHistory();
            console.write(String.valueOf(resList));
        });

        removeByUSABoxOfficeButton.addActionListener(e -> {
            try {
                res = service.sendRequest(new Request(
                        "remove_all_by_usa_box_office", removeByUSABoxOfficeField.getText(), service.getUser()
                ));
                historyManager.add("remove_all_by_usa_box_office");
                console.write(res);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        addIfMaxButton.addActionListener(e -> {
            try {
                res = service.sendRequest(new Request("add_if_max", addIfMaxField.getText(), service.getUser()));
                console.write(res);
                historyManager.add("add_if_max");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        addIfMinButton.addActionListener(e -> {
            try {
                res = service.sendRequest(new Request("add_if_min", addIfMinField.getText(), service.getUser()));
                console.write(res);
                historyManager.add("add_if_min");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public void build(){
        initComponents();
        add(helpLabel); add(helpField); add(helpButton);
        add(infoLabel); add(infoField); add(infoButton);
        add(showLabel); add(showField); add(showButton);
        add(addLabel); add(addField); add(addButton);
        add(updateLabel); add(updateField); add(updateButton);
        add(removeIdLabel); add(removeIdField); add(removeIdButton);
        add(executeScriptLabel); add(executeScriptField); add(executeScriptButton);
        add(historyLabel); add(historyField); add(historyButton);
        add(removeByUSABoxOfficeLabel); add(removeByUSABoxOfficeField); add(removeByUSABoxOfficeButton);
        add(addIfMaxLabel); add(addIfMaxField); add(addIfMaxButton);
        add(addIfMinLabel); add(addIfMinField); add(addIfMinButton);
        updateLanguage();
    }

    public void updateLanguage(){
        helpLabel.setText(lm.getLang().help());
        infoLabel.setText(lm.getLang().info());
        showLabel.setText(lm.getLang().show());
        addLabel.setText(lm.getLang().add());
        updateLabel.setText(lm.getLang().update());
        removeIdLabel.setText(lm.getLang().removeId());
        executeScriptLabel.setText(lm.getLang().executeScript());
        historyLabel.setText(lm.getLang().history());
        removeByUSABoxOfficeLabel.setText(lm.getLang().removeByUSABoxOffice());
        addIfMaxLabel.setText(lm.getLang().addIfMax());
        addIfMinLabel.setText(lm.getLang().addIfMin());

        helpButton.setText(lm.getLang().execute());
        infoButton.setText(lm.getLang().execute());
        showButton.setText(lm.getLang().execute());
        addButton.setText(lm.getLang().execute());
        updateButton.setText(lm.getLang().execute());
        removeIdButton.setText(lm.getLang().execute());
        executeScriptButton.setText(lm.getLang().execute());
        historyButton.setText(lm.getLang().execute());
        removeByUSABoxOfficeButton.setText(lm.getLang().execute());
        addIfMaxButton.setText(lm.getLang().execute());
        addIfMinButton.setText(lm.getLang().execute());
    }
}
