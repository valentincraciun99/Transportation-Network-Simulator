package view;

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
    private JButton button;


    public AdminView()
    {


    }

    public void initTable(Object[][] data) {

        columnNames = new String[] {"ID", "Role","Email","Password","Company","Age"};
        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        button = new JButton("Remove");
        button.addActionListener(ae -> {
            // check for selected row first
            if(table.getSelectedRow() != -1) {

                //TODO: find user Id for selected row and delete from db before delete from the JTable

                // remove selected row from the model
                model.removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
            }
        });
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
