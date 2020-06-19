package controller;

import datastorage.repositories.UserRepository;
import view.AdminView;

import java.sql.SQLException;

public class AdminController {
    AdminView adminView;
    UserRepository userRepository;

    public AdminController(AdminView adminView, UserRepository userRepository) throws SQLException {
        this.adminView = adminView;
        this.userRepository = userRepository;

        LoadTable();

    }

    private void LoadTable() throws SQLException {

        var rows = this.userRepository.getAllUsersAsString();
        Object[][] rowsAsArray = new Object[rows.size()][];
        for(int i = 0; i < rows.size(); i++) {
            rowsAsArray[i] = rows.get(i).toArray();
        }

        this.adminView.initTable(rowsAsArray);
    }
}
