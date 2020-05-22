package controller;

import datastorage.UserDAO;
import model.UserRole;
import view.CustomerView;
import view.LoginView;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Date;

public class LoginController {
    LoginView loginView;
    UserDAO userDAO;

    public LoginController(LoginView loginView, UserDAO userDAO)
    {
        this.loginView = loginView;
        this.userDAO = userDAO;

        loginView.getLoginButton().addActionListener( e -> Login());
    }

    private void Login()
    {
        var userName = loginView.getTextField().getText();
        var password = loginView.getPasswordField().getPassword();
        var user = userDAO.GetUser(userName, new String(password));

        if(user != null && user.getUserRole() == UserRole.customer
                && user.getSubscription().getEndDate().isAfter( LocalDate.now()))
        {
            //TODO: here should be main page
            new CustomerController(new CustomerView(user));
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
