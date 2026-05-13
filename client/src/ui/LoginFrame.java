package ui;

import Service.ClientService;
import Utility.User;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginFrame extends JFrame {
    public LoginFrame(ClientService service){
        this.setLayout(new BorderLayout());
        this.setSize(500,500);
        this.setTitle("Client");
        this.setLocationRelativeTo(null);

        JLabel LoginLabel = new JLabel("Username");
        LoginField LoginField = new LoginField();
        LoginPanel LoginPanel = new LoginPanel(LoginLabel, LoginField);

        JLabel PassLabel = new JLabel("Password");
        JPasswordField PassField = new PassField();
        PassPanel PassPanel = new PassPanel(PassLabel, PassField);

        JPanel FieldsPanel = new FieldsPanel(LoginPanel, PassPanel);
        JPanel ButtonPanel = new JPanel();
        JButton b1 = new JButton("Login");
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
        JButton b2 = new JButton("Register");
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
        ButtonPanel.add(b1);
        ButtonPanel.add(b2);
        this.add(FieldsPanel, BorderLayout.NORTH);
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
}
