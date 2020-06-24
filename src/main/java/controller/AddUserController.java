package controller;

import datastorage.repositories.UserRepository;
import model.User;
import model.enums.UserRole;
import view.AddUserView;

import javax.swing.*;
import java.sql.SQLException;

public class AddUserController {
    AddUserView addUserView;
    UserRepository userRepository;

    public AddUserController(AddUserView addUserView, UserRepository userRepository) {
        this.addUserView = addUserView;
        this.userRepository = userRepository;

        addUserView.getAddUserButton().addActionListener(ae->{
            try {
            Integer userRole = 0;
            String email= this.addUserView.getEmail().getText();
            String password = this.addUserView.getPassword().getText();
            String company = this.addUserView.getCompany().getText();
            Integer age = Integer.parseInt(this.addUserView.getAge().getText());


                userRepository.create(new User(UserRole.customer,email,password,company,age));
                JOptionPane.showMessageDialog(addUserView,"Success");
                addUserView.dispose();
            } catch (SQLException | NumberFormatException  throwables) {
                JOptionPane.showMessageDialog(addUserView,"Error");
                throwables.printStackTrace();
            }
        });
    }
}
