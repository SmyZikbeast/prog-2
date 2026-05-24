package ui;

import Service.ClientService;
import Service.MovieController;
import localization.Localizable;
import localization.LocalizationManager;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FilmList extends JPanel implements Localizable {
    private JTable table;
    private LocalizationManager lm;
    public FilmList(ClientService service, MovieController controller, LocalizationManager lm) {
        this.lm = lm;
        table = new JTable(
                service.getTableModel()
        );
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);
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
    @Override
    public void updateLanguage(){
        table.getColumnModel().getColumn(0).setHeaderValue(lm.getLang().id());
        table.getColumnModel().getColumn(1).setHeaderValue(lm.getLang().name());
        table.getColumnModel().getColumn(2).setHeaderValue(lm.getLang().creationDate());
        table.getColumnModel().getColumn(3).setHeaderValue(lm.getLang().oscarsCount());
        table.getColumnModel().getColumn(4).setHeaderValue(lm.getLang().goldenPalmCount());
        table.getColumnModel().getColumn(5).setHeaderValue(lm.getLang().usaBoxOffice());
        table.getColumnModel().getColumn(6).setHeaderValue(lm.getLang().MPAARating());
        table.getColumnModel().getColumn(7).setHeaderValue(lm.getLang().username());
        table.getTableHeader().repaint();
        repaint();
        revalidate();
    }
}