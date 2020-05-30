package controller;

import business.NodeAdditionService;
import controller.enums.CurrentAction;
import datastorage.repositories.ConfigurationRepository;
import model.Configuration;
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

public class CustomerController {
    CustomerView customerView ;
    CurrentAction currentAction;
    NodeAdditionService nodeAdditionService;
    ConfigurationRepository configurationRepository;
    Configuration configuration;
    BufferedImage image;

    public CustomerController (CustomerView customerView, ConfigurationRepository configurationRepository, NodeAdditionService nodeAdditionService)
    {
        this.customerView = customerView;
        this.nodeAdditionService = nodeAdditionService;
        this.configurationRepository = configurationRepository;
        currentAction = CurrentAction.none;

        try {
            LoadImage();
            LoadConfiguration();
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
        nodeAdditionService.Execute(new NodeAdditionRequest(configuration.getId(),nodeName,x,y));

        DrawNode(nodeName,x,y);
    }

    private void LoadImage() throws IOException {
        var file = new File("C:\\Users\\Valentin\\AppData\\Local\\gara.png");
        image = ImageIO.read(file);
    }

    private void DrawNode(String nodeName, Integer x,Integer y){
        if(nodeName != null) {
            customerView.drawNodeTextField(x,y,nodeName,image);
            customerView.drawNode(x,y,image);
        }
    }

}
