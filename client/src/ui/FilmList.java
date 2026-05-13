package ui;

import Service.ClientService;
import Service.MovieController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FilmList extends JPanel {
    private JTable table;
    public FilmList(ClientService service, MovieController controller) {
        table = new JTable(
                service.getTableModel()
        );
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    controller.openMovieEditor(row);
                }
            }
        });
        setLayout(new BorderLayout());
        add(new JScrollPane(table));
    }
}