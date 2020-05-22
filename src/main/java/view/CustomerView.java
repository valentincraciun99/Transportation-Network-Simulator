package view;

import model.User;

import javax.swing.*;
import java.awt.*;

public class CustomerView {
    private JFrame frame;
    private JLabel mainLabel;
    private JLabel buttonsLabel;
    private JLabel drawingLabel;
    private JLabel userInfo;
    private JTextArea userCredentialsTextField;
    private JButton button = new JButton();

    User customer;


    public CustomerView(User customer)
    {
        this.customer = customer;
        initTextField();
        initUserInfoLabel();
        initDrawingLabel();
        initButtonsLabel();
        initMainLabel();
        initFrame();
    }

    private void initTextField() {
        userCredentialsTextField = new JTextArea();

        userCredentialsTextField.setBounds(300,20,150,30);
        String text = customer.getEmail() + '\n'+ customer.getCompany();
        userCredentialsTextField.setLineWrap(true);
        userCredentialsTextField.setText(text);
        userCredentialsTextField.setEditable(false);
        userCredentialsTextField.setVisible(true);


    }

    private void initUserInfoLabel() {
        userInfo = new JLabel();
    }

    private void initMainLabel() {
        mainLabel = new JLabel();
        mainLabel.setBounds(0,0,800,600);
        mainLabel.add(buttonsLabel,0);
        mainLabel.add(drawingLabel,1);
    }

    private void initDrawingLabel() {
        drawingLabel = new JLabel();
        drawingLabel.setBounds(10,130,765,500);
        drawingLabel.setBackground(Color.white);
        drawingLabel.setOpaque(true);
        drawingLabel.setVisible(true);


    }

    public JButton getButton() {
        return button;
    }

    private void initButtonsLabel() {
        buttonsLabel = new JLabel();
        buttonsLabel.setBounds(10,10,765,100);
        buttonsLabel.setBackground(Color.RED);
        buttonsLabel.setOpaque(true);
        buttonsLabel.setVisible(true);

        buttonsLabel.add(userCredentialsTextField);
        buttonsLabel.add(button);
        button.setVisible(true);
        button.setBounds(20,20,150,50);


    }

    private void initFrame() {
        frame = new JFrame("Main");
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.setContentPane(mainLabel);

        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JLabel getMainLabel() {
        return mainLabel;
    }

    public JLabel getButtonsLabel() {
        return buttonsLabel;
    }

    public JLabel getDrawingLabel() {
        return drawingLabel;
    }

    public JLabel getUserInfo() {
        return userInfo;
    }

    public User getCustomer() {
        return customer;
    }
}
