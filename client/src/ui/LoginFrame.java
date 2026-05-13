package ui;

import Service.ClientService;
import Utility.User;
import localization.LocalizationManager;
import localization.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginFrame extends JFrame implements Localizable{
    JLabel LoginLabel;
    JLabel PassLabel;
    JButton b1;
    JButton b2;
    LocalizationManager lm;
    public LoginFrame(ClientService service, LocalizationManager lm){
        this.lm = lm;
        this.setLayout(new BorderLayout());
        this.setSize(500,500);
        this.setTitle("Client");
        this.setLocationRelativeTo(null);

        LoginLabel = new JLabel(lm.getLang().username());
        LoginField LoginField = new LoginField();
        LoginPanel LoginPanel = new LoginPanel(LoginLabel, LoginField);

        PassLabel = new JLabel(lm.getLang().password());
        JPasswordField PassField = new PassField();
        PassPanel PassPanel = new PassPanel(PassLabel, PassField);

        JPanel FieldsPanel = new FieldsPanel(LoginPanel, PassPanel);
        JPanel ButtonPanel = new JPanel();
        b1 = new JButton(lm.getLang().login());
        b1.addActionListener( e -> {
            try {
                User user = new User(LoginField.getText(), String.valueOf(PassField.getPassword()));
                service.authorize(user);
                Thread.sleep(100);
                boolean is_logged = service.getUserState();
                System.out.println(service.getUserState()) ;
                this.setVisible(!is_logged);
            }
            catch (NullPointerException | IOException ex){
                System.out.println("user is silly");
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        b2 = new JButton(lm.getLang().register());
        b2.addActionListener( e -> {
            try {
                User user = new User(LoginField.getText(), String.valueOf(PassField.getPassword()));
                service.register(user);
                boolean is_logged = service.getUserState();
                this.setVisible(!is_logged);
            }
            catch (NullPointerException ex){
                System.out.println("user is silly");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        JPanel header = new JPanel();
        JComboBox<String> langBox = new JComboBox<>(
                new String[]{"RU", "SV", "NO", "ES"}
        );
        langBox.addActionListener(e -> {
            String selected = (String) langBox.getSelectedItem();
            lm.setLanguage(selected);
            switch (selected) {
                case "RU" -> lm.setLang(new RuLang());
                case "SV" -> lm.setLang(new SeLang());
                case "NO" -> lm.setLang(new NoLang());
                case "ES" -> lm.setLang(new EsLang());
            }
            updateLanguage();
        });
        header.add(langBox);
        this.add(header,BorderLayout.NORTH);
        ButtonPanel.add(b1);
        ButtonPanel.add(b2);
        this.add(FieldsPanel, BorderLayout.CENTER);
        this.add(ButtonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    class LoginField extends JTextField{
        LoginField(){
            super(15);
            this.setMaximumSize(new Dimension(200,30));
            this.setMinimumSize(new Dimension(200,30));
        }
    }
    class LoginPanel extends JPanel{
        LoginPanel(JLabel LoginLabel, JTextField LoginField){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(LoginLabel);
            this.add(LoginField);
        }
    }
    class PassField extends JPasswordField{
        PassField(){
            super(15);
            this.setMaximumSize(new Dimension(200,30));
            this.setMinimumSize(new Dimension(200,30));
        }
    }
    class PassPanel extends JPanel{
        PassPanel(JLabel PassLabel, JPasswordField PassField){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(PassLabel);
            this.add(PassField);
        }
    }
    class FieldsPanel extends JPanel{
        FieldsPanel(LoginPanel LoginPanel, PassPanel PassPanel){
            this.setPreferredSize(new Dimension(400,100));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(LoginPanel);
            this.add(PassPanel);
        }
    }
    @Override
    public void updateLanguage(){
        LoginLabel.setText(lm.getLang().username());
        PassLabel.setText(lm.getLang().password());
        b1.setText(lm.getLang().login());
        b2.setText(lm.getLang().register());
        repaint();
        revalidate();
    }
}
