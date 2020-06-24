package view;

import javax.swing.*;
import java.awt.*;

public class AddUserView extends JFrame {
    JTextField email;
    JTextField password;
    JTextField company;
    JTextField age;
    JButton addUserButton;
    JLabel label;

    public JTextField getEmail() {
        return email;
    }

    public JTextField getPassword() {
        return password;
    }

    public JTextField getCompany() {
        return company;
    }

    public JTextField getAge() {
        return age;
    }

    public JButton getAddUserButton() {
        return addUserButton;
    }

    public JLabel getLabel() {
        return label;
    }

    public AddUserView()
    {
        label = new JLabel();
        label.setVisible(true);
        label.setBackground(Color.BLACK);
        label.setBounds(0,0,400,400);


        email = new JTextField();
        email.setBounds(50,10,200,30);
        email.setVisible(true);

        label.add(email);

        password = new JTextField();
        password.setBounds(50,50,200,30);
        password.setVisible(true);
        label.add(password);

        company = new JTextField();
        company.setBounds(50,90,200,30);
        company.setVisible(true);
        label.add(company);

        age = new JTextField();
        age.setBounds(50,130,200,30);
        age.setVisible(true);
        label.add(age);

        addUserButton = new JButton("Add new user");
        addUserButton.setBounds(50,170,100,30);
        addUserButton.setVisible(true);
        label.add(addUserButton);

        setSize(300,300);
        setResizable(false);
        setBackground(Color.BLACK);
        add(label);
        setVisible(true);
    }
}
