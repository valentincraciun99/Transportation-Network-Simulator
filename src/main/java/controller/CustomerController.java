package controller;

import business.NodeAdditionService;
import controller.enums.CurrentAction;
import datastorage.repositories.ConfigurationRepository;
import datastorage.repositories.NodeRepository;
import model.Configuration;
import model.Node;
import model.request.NodeAdditionRequest;
import view.CustomerView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController {
    CustomerView customerView ;
    CurrentAction currentAction;
    NodeAdditionService nodeAdditionService;
    ConfigurationRepository configurationRepository;
    NodeRepository nodeRepository;
    Configuration configuration;
    ArrayList<Node> nodes;

    public CustomerController (CustomerView customerView, ConfigurationRepository configurationRepository, NodeRepository nodeRepository, NodeAdditionService nodeAdditionService)
    {
        this.customerView = customerView;
        this.nodeAdditionService = nodeAdditionService;
        this.configurationRepository = configurationRepository;
        this.nodeRepository = nodeRepository;
        currentAction = CurrentAction.none;

        customerView.getButtonLoadLastConfiguration().addActionListener(e->{

            customerView.getButtonLoadLastConfiguration().setVisible(false);
            customerView.getMainLabel().remove(customerView.getButtonLoadLastConfiguration());

            drawInitialNodes();

        });

        try {
            LoadImage();
            LoadConfiguration();
            LoadNodes();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        customerView.getDrawingLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {

                    //TODO:add condition for drawing node
                    if(currentAction.equals(CurrentAction.drawNode))
                    {
                        addNewNode(e.getX(),e.getY());
                    }

                } catch (IOException | SQLException ioException) {
                    ioException.printStackTrace();
                }

            }
        });


        customerView.getButtonSetFlagToDrawing().addActionListener(e-> {
            currentAction = !(currentAction.equals( CurrentAction.drawNode))?CurrentAction.drawNode:CurrentAction.none;

        });


    }

    private void drawInitialNodes() {

        for(var node:nodes)
        {
            drawNode(node.getName(),node.getX(),node.getY());
        }
    }

    private void LoadNodes() throws SQLException {
        nodes = nodeRepository.getAllNodesFromConfiguration(configuration.getId());

    }

    private void LoadConfiguration() throws SQLException {
        try {
            configuration = configurationRepository.getByUserId(customerView.getCustomer().getId());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

       if(configuration == null)
       {
           String configurationName = (String) JOptionPane.showInputDialog(
                   customerView.getDrawingLabel(),
                   "Add configuration name",
                   "Config Name",
                   JOptionPane.PLAIN_MESSAGE,
                   null,
                   null,
                   null
           );
           if(configurationName != null)
               configuration = configurationRepository.create(new Configuration(configurationName,customerView.getCustomer().getId()));
       }

    }

    private void addNewNode(int x, int y) throws IOException, SQLException {

        String nodeName = (String) JOptionPane.showInputDialog(
                customerView.getDrawingLabel(),
                "Add a name for current node",
                "Node Name",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null
        );
        nodes.add(
                nodeAdditionService.Execute(new NodeAdditionRequest(configuration.getId(),nodeName,x,y))
        );

        drawNode(nodeName,x,y);
    }

    private void LoadImage() throws IOException {
        var file = new File("C:\\Users\\Valentin\\AppData\\Local\\gara.png");
        customerView.setImage(ImageIO.read(file));
    }

    private void drawNode(String nodeName, Integer x,Integer y){

            customerView.drawNode(nodeName,x,y);
            customerView.drawNodeTextField(x,y,nodeName);
    }

}
