package ui;

import Manager.CollectionManager;
import Swing.MovieTableModel;

import javax.swing.*;
import java.awt.*;

public class FilmTab extends JPanel {
    private JTable table;
    private CollectionManager cm;
    MovieTableModel model = new MovieTableModel();
    public FilmTab() {
        table = new JTable(
                model
        );
        table.getTableHeader().setReorderingAllowed(false);
        setLayout(new BorderLayout());
        add(new JScrollPane(table));

    }
    public void setCm(CollectionManager cm) {
        this.cm = cm;
    }
    public void startAutoRefresh(){
        new Thread(() -> {
            while(true){
                try {
                    Thread.sleep(100);
                    model.setMovies(cm.getCollection());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
