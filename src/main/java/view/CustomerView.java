package view;

import model.Node;
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
    private JButton loadLastConfiguration;
    private JTextArea userCredentialsTextField;
    private JButton buttonSetFlagToDrawing;
    private BufferedImage image;



    private ArrayList<JTextField> nodesTextFields= new ArrayList<>();

    private final User customer;


    public CustomerView(User customer)
    {
        this.customer = customer;

        initTextField();
        initUserInfoLabel();
        initButtons();
        initDrawingLabel();
        initButtonsLabel();
        initMainLabel();
        initFrame();
    }

    private void initButtons() {
        buttonSetFlagToDrawing = new JButton();
        buttonSetFlagToDrawing.setVisible(true);
        buttonSetFlagToDrawing.setBounds(20,20,150,50);
        buttonSetFlagToDrawing.setText("Add Node");


        loadLastConfiguration =new JButton();
        loadLastConfiguration.setVisible(true);
        loadLastConfiguration.setBounds(300,111,150,20);
        loadLastConfiguration.setText("Load last configuration");
        loadLastConfiguration.setBackground(Color.ORANGE);
        loadLastConfiguration.setOpaque(true);


    }

    public void drawNodeTextField(Integer x, Integer y, String nodeName)
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

    public void drawNode(String nodeName,Integer x,Integer y)
    {
        if(nodeName != null) {
            Graphics g = drawingLabel.getGraphics();
            g.drawImage(image, x - image.getWidth() / 2, y - image.getHeight() / 2, null);
        }
    }

    private void initTextField() {
        userCredentialsTextField = new JTextArea();

        userCredentialsTextField.setBounds(650,0,150,50);
        String text = customer.getEmail() + '\n'+ customer.getCompany();
        userCredentialsTextField.setLineWrap(true);
        userCredentialsTextField.setText(text);
        userCredentialsTextField.setBackground(Color.LIGHT_GRAY);
        userCredentialsTextField.setEditable(false);
        userCredentialsTextField.setVisible(true);


    }

    private void initUserInfoLabel() {
       // userInfo = new JLabel();
    }

    private void initMainLabel() {
        mainLabel = new JLabel();
        mainLabel.setBounds(0,0,800,600);
        mainLabel.add(buttonsLabel,0);
        mainLabel.add(drawingLabel,1);
        mainLabel.add(loadLastConfiguration,2);
    }

    private void initDrawingLabel() {
        drawingLabel = new JLabel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

            }

        };
        drawingLabel.setBounds(10,130,765,500);
        drawingLabel.setBackground(Color.LIGHT_GRAY);
        drawingLabel.setOpaque(true);
        drawingLabel.setVisible(true);


    }


    public JButton getButtonSetFlagToDrawing() {
        return buttonSetFlagToDrawing;
    }

    private void initButtonsLabel() {
        buttonsLabel = new JLabel();

        buttonsLabel.add(userCredentialsTextField);
        buttonsLabel.add(buttonSetFlagToDrawing);

        buttonsLabel.setBounds(10,10,765,100);
        buttonsLabel.setBackground(Color.RED);
        buttonsLabel.setOpaque(true);
        buttonsLabel.setVisible(true);



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


    public User getCustomer() {
        return customer;
    }


    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public JButton getButtonLoadLastConfiguration(){
        return loadLastConfiguration;
    }
}
