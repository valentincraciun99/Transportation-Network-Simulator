package view;

import view.table.UsersTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminView extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    //private Object[][] data;
    private String[] columnNames;
    private JButton deleteButton;
    private JButton addButton;


    public AdminView()
    {
        InitButtons();

    }

    public JButton getAddButton() {
        return addButton;
    }

    private void InitButtons() {
        deleteButton = new JButton("Remove");
        deleteButton.setForeground(Color.RED);
        addButton =  new JButton("Add");
        addButton.setForeground(Color.darkGray);
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void initTable(Object[][] data) {

        columnNames = new String[] {"ID", "Role","Email","Password","Company","Age"};
        model = new UsersTable(data, columnNames);
        table = new JTable(model);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);
        add(addButton,BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
