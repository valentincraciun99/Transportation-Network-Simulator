package view;

import model.User;
import view.tools.*;

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
    private JButton addNodeButton;
    private BufferedImage image;
    private JButton addEdgeButton;
    private JButton shortestPathButton;
    public Arrow arrow;



    private ArrayList<JTextField> nodesTextFields= new ArrayList<>();

    private final User customer;


    public CustomerView(User customer,Arrow arrow)
    {
        this.customer = customer;
        this.arrow = arrow;

        initTextField();
        initUserInfoLabel();
        initButtons();
        initDrawingLabel();
        initButtonsLabel();
        initMainLabel();
        initFrame();
    }


    private void initButtons() {
        addNodeButton = new JButton();
        addNodeButton.setVisible(true);
        addNodeButton.setBounds(20,20,150,50);
        addNodeButton.setText("Add Node");


        addEdgeButton = new JButton();
        addEdgeButton.setVisible(true);
        addEdgeButton.setBounds(200,20,150,50);
        addEdgeButton.setText("Add Edge");


        loadLastConfiguration =new JButton();
        loadLastConfiguration.setVisible(true);
        loadLastConfiguration.setBounds(300,111,150,20);
        loadLastConfiguration.setText("Load last configuration");
        loadLastConfiguration.setBackground(Color.ORANGE);
        loadLastConfiguration.setOpaque(true);

        shortestPathButton = new JButton();
        shortestPathButton.setBounds(380,20,150,50);
        shortestPathButton.setText("Find Optimal path");
        shortestPathButton.setVisible(true);


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
        drawingLabel.setBackground(Color.WHITE);
        drawingLabel.setOpaque(true);
        drawingLabel.setVisible(true);


    }


    public JButton getAddNodeButton() {
        return addNodeButton;
    }

    private void initButtonsLabel() {
        buttonsLabel = new JLabel();

        buttonsLabel.add(userCredentialsTextField);
        buttonsLabel.add(addNodeButton);
        buttonsLabel.add(addEdgeButton);
        buttonsLabel.add(shortestPathButton);

        buttonsLabel.setBounds(10,10,765,100);
        buttonsLabel.setBackground(Color.WHITE);
        buttonsLabel.setOpaque(true);
        buttonsLabel.setVisible(true);



    }

    private void initFrame() {
        frame = new JFrame("Main");
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 650);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLACK);

        frame.setResizable(false);

        frame.setContentPane(mainLabel);

        frame.setVisible(true);
    }


    public JButton getAddEdgeButton() {
        return addEdgeButton;
    }

    public JButton getShortestPathButton(){return shortestPathButton;}
    public BufferedImage getImage() {
        return image;
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
