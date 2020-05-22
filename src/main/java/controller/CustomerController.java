package controller;

import view.CustomerView;

import java.awt.*;

public class CustomerController {
    CustomerView customerView ;
    Graphics g;
    public CustomerController (CustomerView customerView)
    {
        this.customerView = customerView;

        g=customerView.getDrawingLabel().getGraphics();
        g.setColor(Color.black);
        g.drawLine(100,200,300,400);
        customerView.getButton().addActionListener(e-> {
            try {
                draw();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });


    }

    void draw() throws InterruptedException {
        g.drawLine(100,200,300,400);
        Thread.sleep(2000);

        customerView.getDrawingLabel().repaint();

    }



}
