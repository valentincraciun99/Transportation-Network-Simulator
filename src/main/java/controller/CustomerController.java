package controller;

import view.CustomerView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomerController {
    CustomerView customerView ;
    Graphics g;
    public CustomerController (CustomerView customerView)
    {
        this.customerView = customerView;

        g=customerView.getDrawingLabel().getGraphics();

        customerView.getDrawingLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {

                    //TODO:add condition for drawing node
                    if(true)
                    {
                        addNewNode(e.getX(),e.getY());
                    }

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }




            }
        });

 
        customerView.getButton().addActionListener(e-> {
            try {
                draw();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });


    }

    private void addNewNode(int x, int y) throws IOException {

        String nodeName = (String) JOptionPane.showInputDialog(
                customerView.getDrawingLabel(),
                "Add a name for current node",
                "Node Name",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null
        );
        //TODO: Verify if node is saved in DB FIRST

        var file = new File("C:\\Users\\Valentin\\AppData\\Local\\gara.png");
        final BufferedImage image = ImageIO.read(file);

        if(nodeName != null) {
            customerView.drawNodeTextField(x,y,nodeName,image);
            customerView.drawNode(x,y,image);
        }

    }

    void draw() throws InterruptedException {
        /*g.drawLine(100,200,300,400);
        Thread.sleep(2000);*/

        customerView.getDrawingLabel().repaint();

    }



}
