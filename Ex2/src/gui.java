import javax.swing.*;
import java.awt.*;
import api.*;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import javax.swing.JFrame;


public class gui extends JPanel{

    DirectedWeightedGraph_c graph_c ;
    public gui (DirectedWeightedGraph_c g){
        this.graph_c = g;
        this.repaint();
    }
    public void paint(Graphics g) {
        super.paint(g);
       //graph_c.load("C:\\users\\amit ginzberg\\IdeaProjects\\OOP_2021\\Assignments\\Ex2\\src\\data\\G3.json");
        g.setFont(new Font("MV Boli", Font.PLAIN, 25)); //set font of text
        g.setColor(Color.white);
        //g.drawString(String.valueOf(isConnected), 100, 100);
        g.setColor(Color.red);
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
        double maxX = 0, maxY = 0;
        if (graph_c.getNodes() != null) {
        for (int i = 0; i < graph_c.nodeSize(); i++) {
            Node n = (Node) graph_c.getNode(i);
            if (n.getLocation().x() > maxX) {
                maxX = n.getLocation().x();
            }
            if (n.getLocation().x() < minX) {
                minX = n.getLocation().x();
            }
            if (n.getLocation().y() > maxY) {
                maxY = n.getLocation().y();
            }
            if (n.getLocation().y() < minY) {
                minY = n.getLocation().y();
            }
        }
        double absX = Math.abs(maxX - minX);
        double absY = Math.abs(maxY - minY);
        double scaleX = getSize().getWidth() / absX;
        double scaleY = getSize().getHeight() / absY;
        EdgeData e;

            for (int i = 0; i < graph_c.edgeSize(); i++) {
                for (int j = 0; j < graph_c.edgeSize(); j++) {
                    if (graph_c.getEdge(i, j) != null) {
                        e = graph_c.getEdge(i, j);
                        Node src = (Node) graph_c.getNode(i);
                        Node dest = (Node) graph_c.getNode(j);
                        double src_x = (src.getLocation().x() * scaleX) % getSize().getWidth();
                        double src_y = (src.getLocation().y() * scaleY) % getSize().getHeight();
                        double dest_x = (dest.getLocation().x() * scaleX) % getSize().getWidth();
                        double dest_y = (dest.getLocation().y() * scaleY) % getSize().getHeight();
                        Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(3));
                        g2.setColor(Color.BLUE);
                        g2.draw(new Line2D.Float((int) src_x, (int) src_y, (int) dest_x, (int) dest_y));
                    }
                }
            }
            for (int i = 0; i < graph_c.nodeSize(); i++) {
                g.setColor(Color.red);
                Node n = (Node) graph_c.getNode(i);
                g.fillOval((int) ((n.getLocation().x() * scaleX) % getSize().getWidth()), ((int) ((n.getLocation().y() * scaleY) % getSize().getHeight())), 10, 10);
            }
        }
    }

}
