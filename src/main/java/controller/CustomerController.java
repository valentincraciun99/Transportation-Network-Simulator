package controller;

import business.EdgeAdditionService;
import business.NodeAdditionService;
import controller.enums.CurrentAction;
import datastorage.repositories.ConfigurationRepository;
import datastorage.repositories.EdgeRepository;
import datastorage.repositories.NodeRepository;
import model.Configuration;
import model.Edge;
import model.Node;
import model.request.NodeAdditionRequest;
import view.CustomerView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CustomerController {
    CustomerView customerView ;
    CurrentAction currentAction;
    NodeAdditionService nodeAdditionService;
    EdgeAdditionService edgeAdditionService;
    ConfigurationRepository configurationRepository;
    NodeRepository nodeRepository;
    EdgeRepository edgeRepository;
    Configuration configuration;
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;
    Queue<Node> selectedNodes;

    public CustomerController (CustomerView customerView, ConfigurationRepository configurationRepository
            , NodeRepository nodeRepository,EdgeRepository edgeRepository, NodeAdditionService nodeAdditionService,EdgeAdditionService edgeAdditionService)
    {
        this.customerView = customerView;
        this.nodeAdditionService = nodeAdditionService;
        this.edgeAdditionService = edgeAdditionService;
        this.configurationRepository = configurationRepository;
        this.nodeRepository = nodeRepository;
        this.edgeRepository = edgeRepository;


        selectedNodes =  new LinkedList<>();
        currentAction = CurrentAction.none;

        customerView.getButtonLoadLastConfiguration().addActionListener(e->{

            customerView.getButtonLoadLastConfiguration().setVisible(false);
            customerView.getMainLabel().remove(customerView.getButtonLoadLastConfiguration());

            drawInitialNodes();
            drawInitialEdges();

        });

        try {
            LoadImage();
            LoadConfiguration();
            LoadNodes();
            LoadEdges();
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
                    else if(currentAction.equals(CurrentAction.drawEdge))
                    {
                        Node node = findNode(e.getX(),e.getY());

                        if(node != null)
                        {
                            selectedNodes.add(node);
                        }

                        if(selectedNodes.size()>=2)
                        {
                            Node firstNode = selectedNodes.poll();
                            Node secondNode = selectedNodes.poll();

                            Edge edge = edgeAdditionService.Execute(new Edge(firstNode.getId(),secondNode.getId()
                                    ,euclidianDistance(firstNode.getX(),firstNode.getY(),secondNode.getX(),secondNode.getY()),configuration.getId()));

                            if(edge!=null) {
                                customerView.arrow.draw(firstNode.getX() - 5, firstNode.getY() - 5
                                        , secondNode.getX() + 5, secondNode.getY() + 5
                                        , Color.DARK_GRAY, Color.ORANGE, 4
                                        , customerView.getDrawingLabel().getGraphics());
                                edges.add(edge);
                            }
                        }


                    }

                } catch (IOException | SQLException ioException) {
                    ioException.printStackTrace();
                }

            }
        });


        customerView.getAddNodeButton().addActionListener(e-> {
            currentAction = !(currentAction.equals( CurrentAction.drawNode))?CurrentAction.drawNode:CurrentAction.none;

        });

        customerView.getAddEdgeButton().addActionListener(e->{
            currentAction = !(currentAction.equals( CurrentAction.drawEdge))?CurrentAction.drawEdge:CurrentAction.none;
        });

    }

    private void drawInitialEdges() {
        Node firstNode =null;
        Node secondNode =null;
        for(var edge:edges)
        {
            for(var node:nodes)
            {
                if(node.getId() == edge.getNode_from())
                    firstNode = node;
                if(node.getId() == edge.getNode_to())
                    secondNode = node;
            }
            if(firstNode !=  null && secondNode !=null) {

                customerView.arrow.draw(firstNode.getX() - 5, firstNode.getY() - 5
                        , secondNode.getX() + 5, secondNode.getY() + 5
                        , Color.DARK_GRAY, Color.ORANGE, 4
                        , customerView.getDrawingLabel().getGraphics());
            }

        }
    }

    private void LoadEdges() throws SQLException {
        edges = edgeRepository.getAllEdgesFromConfiguration(configuration.getId());

    }

    static Integer euclidianDistance(int x1, int y1, int x2, int y2)
    {
        // Calculating distance
        return (int) (Math.sqrt(Math.pow(x2 - x1, 2) +
                Math.pow(y2 - y1, 2) * 1.0));
    }

    private Node findNode(int x, int y) {

        var imgHeight = customerView.getImage().getHeight();
        var imgWidth = customerView.getImage().getWidth();

        for(var node:nodes)
        {
            if((x>= node.getX() - imgWidth/2) && (x<= node.getX() + imgWidth/2)
                    &&  (y>= node.getY() - imgHeight/2) && (y<= node.getY()+imgHeight/2))
                return node;
        }
    return null;
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
