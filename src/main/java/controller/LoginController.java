package controller;

import business.EdgeAdditionService;
import business.NodeAdditionService;
import datastorage.repositories.ConfigurationRepository;
import datastorage.repositories.EdgeRepository;
import datastorage.repositories.NodeRepository;
import datastorage.repositories.UserRepository;
import model.enums.UserRole;
import view.CustomerView;
import view.LoginView;
import view.tools.Arrow;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class LoginController {
    LoginView loginView;
    UserRepository userRepository;

    public LoginController(LoginView loginView, UserRepository userRepository)
    {
        this.loginView = loginView;
        this.userRepository = userRepository;

        loginView.getLoginButton().addActionListener( e -> {
            try {
                Login();
            } catch (SQLException throwables) {
                JOptionPane.showMessageDialog(loginView.getFrame(),"Invalid credentials");
                throwables.printStackTrace();
            }
        });
    }

    //TODO: if catch here sql exception if credentials dosen't exists
    private void Login() throws SQLException {
        var userName = loginView.getTextField().getText();
        var password = loginView.getPasswordField().getPassword();
        var user = userRepository.get(userName, new String(password));

        if(user != null && user.getUserRole() == UserRole.customer
                /*&& user.getSubscription().getEndDate().isAfter( LocalDate.now())*/)
        {
            //TODO: here should be main page
            new CustomerController(new CustomerView(user, new Arrow()),new ConfigurationRepository(),new NodeRepository(),new NodeAdditionService(new NodeRepository()),new EdgeAdditionService(new EdgeRepository()));

            //TODO: add an event in main to create customerController and dispose login
            loginView.getFrame().setVisible(false);
        }
        else if (user != null && user.getUserRole() == UserRole.customer
                && user.getSubscription().getEndDate().isBefore( LocalDate.now()))
        {
            JOptionPane.showMessageDialog(loginView.getFrame(),"Subscription expired!");
        }
        else if (user != null && user.getUserRole() == UserRole.admin)
        {
            JOptionPane.showMessageDialog(loginView.getFrame(),"Here Should Be Admin Page");
            //TODO: add here admin jframe
        }

    }


}
