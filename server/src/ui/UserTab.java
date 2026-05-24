package ui;

import Swing.UserTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class UserTab extends JPanel {
    private JTable table;
    UserTableModel model = new UserTableModel();
    UserTab() {
        table = new JTable(
                model
        );
        table.getTableHeader().setReorderingAllowed(false);
        setLayout(new BorderLayout());
        add(new JScrollPane(table));
    }
    public void update(HashSet<String> userSet) {
        model.setUserSet(userSet);
    }
}
