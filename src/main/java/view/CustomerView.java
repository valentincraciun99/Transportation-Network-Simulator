package view;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CustomerView {
    private JFrame frame;
    private JLabel mainLabel;
    private JLabel buttonsLabel;
    private JLabel drawingLabel;
    private JLabel userInfo;
    private JTextArea userCredentialsTextField;
    private JButton button = new JButton();
    private ArrayList<JTextField> nodesTextFields= new ArrayList<>();

    private final User customer;


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

    public void drawNodeTextField(Integer x, Integer y, String nodeName, BufferedImage image)
    {
        var nodeTextField = new JTextField();
        nodeTextField.setBounds(x - image.getWidth() / 2, y - image.getHeight() / 2 - 25, image.getWidth(), 25);
        nodeTextField.setEditable(false);
        nodeTextField.setBackground(Color.WHITE);
        nodeTextField.setSelectedTextColor(Color.BLACK);
        nodeTextField.setText(nodeName);
        nodeTextField.setVisible(true);
        nodesTextFields.add(nodeTextField);
        drawingLabel.add(nodeTextField);

    }

    public void drawNode(Integer x,Integer y,BufferedImage image)
    {
        Graphics g = drawingLabel.getGraphics();
        g.drawImage(image, x - image.getWidth() / 2, y - image.getHeight() / 2, null);
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
        drawingLabel.setBackground(Color.black);
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
