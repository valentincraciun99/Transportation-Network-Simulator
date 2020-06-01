package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class LoginView {

    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel label;
    private BufferedImage logo;

    public LoginView()
    {

        initTextField();
        initPasswordField();
        initJButton();
        initLabel();
        initFrame();
    }

    private void initLabel() {

        label = new JLabel();
        label.setBounds(0,0,350,400);
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        label.setVisible(true);

        label.add(passwordField);
        label.add(loginButton);
        label.add(textField);
    }

    private void initJButton() {
        loginButton = new JButton("Login");
        loginButton.setBounds(130,260,80,30);
        loginButton.setOpaque(true);
        loginButton.setBackground(Color.BLUE);
        loginButton.setBorderPainted(true);
        loginButton.setForeground(Color.BLACK);
        loginButton.setVisible(true);


    }

    private void initPasswordField() {

        passwordField = new JPasswordField();
        passwordField.setBounds(40,220,250,25);
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.BLUE);
        passwordField.setVisible(true);

    }

    private void initTextField() {
        textField = new JTextField();
        textField.setBounds(40,180,250,25);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLUE);
        textField.setVisible(true);

    }

    private void initFrame() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.add(label);
        frame.setVisible(true);
    }

    public void drawLogo(Graphics g)
    {
        g.drawImage(logo,78,0,null);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JLabel getLabel() {
        return label;
    }


    public void setLogo(BufferedImage image) {

        logo = image;
    }
}
