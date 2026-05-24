package ui;


import javax.swing.*;

public class MainTab extends JPanel {
    public static volatile Integer uCount = 0;
    JLabel users = new JLabel("User count:");
    JLabel uc = new JLabel(uCount.toString());
    public MainTab(){
        this.add(users);
        this.add(uc);
    }

    public void update(int userCount) {
        uCount = userCount;
        uc.setText(uCount.toString());
    }
}
