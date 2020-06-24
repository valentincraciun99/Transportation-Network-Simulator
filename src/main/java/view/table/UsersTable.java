package view.table;

import javax.swing.table.DefaultTableModel;

public class UsersTable extends DefaultTableModel {
    public UsersTable(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
