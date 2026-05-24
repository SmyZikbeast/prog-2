package Swing;

import BaseFiles.Movie;
import Utility.User;

import javax.swing.table.AbstractTableModel;
import java.util.HashSet;
import java.util.Set;

public class UserTableModel extends AbstractTableModel {
    private HashSet<String> userSet;
    public Object getValueAt(int row, int col) {
        String u = userSet.stream()
                .skip(row)
                .findFirst()
                .orElse(null);

        return switch (col) {
            case 0 -> u;
            default -> "";
        };
    }
    @Override
    public int getRowCount() {
        return userSet.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }
    private final String[] cols = {"Username"};

    public HashSet<String> getUserSet() {
        return userSet;
    }
    @Override
    public String getColumnName(int col) {
        return cols[col];
    }
    public void setUserSet(HashSet<String> userSet) {
        this.userSet = userSet;
        fireTableDataChanged();
    }
}
