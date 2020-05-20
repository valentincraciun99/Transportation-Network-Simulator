package view;

import javax.swing.*;


public class LoginView {

    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel label;

    public LoginView()
    {
        initLabel();
        initTextField();
        initPasswordField();
        initJButton();
        initFrame();
    }

    private void initLabel() {

        label = new JLabel();
        label.setBounds(10,10,400,100);
        label.setVisible(true);

    }

    private void initJButton() {
        loginButton = new JButton("Login");
        loginButton.setBounds(90,80,80,30);
        loginButton.setVisible(true);

        label.add(loginButton);
    }

    private void initPasswordField() {

        passwordField = new JPasswordField();
        passwordField.setBounds(10,45,250,25);

        label.add(passwordField);

    }

    private void initTextField() {
        textField = new JTextField();
        textField.setBounds(10,10,250,25);
        textField.setVisible(true);

        label.add(textField);
    }

    private void initFrame() {
        frame = new JFrame("Login");
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(285, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setContentPane(label);
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


}
