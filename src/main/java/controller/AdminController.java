package controller;

import datastorage.repositories.UserRepository;
import model.User;
import view.AddUserView;
import view.AdminView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class AdminController {
    AdminView adminView;
    UserRepository userRepository;
    List<List<Object>> rows;

    public AdminController(AdminView adminView, UserRepository userRepository) throws SQLException {
        this.adminView = adminView;
        this.userRepository = userRepository;

        this.adminView.getDeleteButton().addActionListener(ae -> {
            // check for selected row first
            if(this.adminView.getTable().getSelectedRow() != -1) {
                var selectedRow = this.adminView.getTable().getSelectedRow();
                Integer selectedUserId = (Integer) this.adminView.getModel().getValueAt(selectedRow,0);
                try {
                    this.userRepository.delete(selectedUserId);
                    // remove selected row from the model
                    this.adminView.getModel().removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                } catch (SQLException throwables) {
                    JOptionPane.showMessageDialog(null, "Error!");
                    throwables.printStackTrace();
                }


            }
        });

        this.adminView.getAddButton().addActionListener(ae->{
            new AddUserView();
        });

        LoadTable();

    }

    private void LoadTable() throws SQLException {

        rows = this.userRepository.getAllUsersAsString();
        Object[][] rowsAsArray = new Object[rows.size()][];
        for(int i = 0; i < rows.size(); i++) {
            rowsAsArray[i] = rows.get(i).toArray();
        }

        this.adminView.initTable(rowsAsArray);
    }
}
