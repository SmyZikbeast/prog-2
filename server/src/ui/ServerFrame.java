package ui;

import Manager.CollectionManager;

import javax.swing.*;

public class ServerFrame extends JFrame {


    private CollectionManager cm;
    MainTab mainTab = new MainTab();
    UserTab userTab = new UserTab();
    FilmTab filmTab = new FilmTab();
    public ServerFrame() {
        this.setTitle("Server");
        JTabbedPane tabs = new JTabbedPane();
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setSize(1000,1000);
        tabs.add("Main", mainTab);
        tabs.add("Users", userTab);
        tabs.add("Films", filmTab);
        this.add(tabs);
        this.setVisible(true);
    }
    public void update(int userCount){
        mainTab.update(userCount);
    }
    public void FTsetCm(CollectionManager cm) {
        filmTab.setCm(cm);
        filmTab.startAutoRefresh();
    }
}
