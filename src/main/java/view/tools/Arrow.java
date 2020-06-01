package view.tools;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Arrow {

    public void draw(int x, int y, int endX, int endY, Color arrowColor, Color topColor, int thickness, Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setColor(arrowColor);
        g2.setStroke(new BasicStroke(thickness));
        g2.drawLine(x, y, endX, endY);
        ;
        drawArrowHead(x,y,endX,endY,topColor,g2);
        g2.dispose();
    }

    private void drawArrowHead(int x, int y, int endX, int endY,Color color, Graphics2D g2) {

        Polygon arrowHead = new Polygon();
        AffineTransform tx = new AffineTransform();

        arrowHead.addPoint(0, 5);
        arrowHead.addPoint(-5, -5);
        arrowHead.addPoint(5, -5);

        tx.setToIdentity();
        double angle = Math.atan2(endY - y, endX - x);
        tx.translate(endX, endY);
        tx.rotate(angle - Math.PI / 2d);

        g2.setTransform(tx);
        g2.setColor(color);
        g2.fill(arrowHead);
    }

}