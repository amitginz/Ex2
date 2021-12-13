import api.GeoLocation;
import api.NodeData;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Menu extends JFrame implements ActionListener,MouseInputListener {
    JMenu menu, menu1, menu2;
    JMenuItem tsp, center, shortestPath, isConnected, init, load, save, addnode,connect,removeNode,removeEdge;
    DirectedWeightedGraphAlgorithms_c algo = new DirectedWeightedGraphAlgorithms_c(null);
    DirectedWeightedGraph_c graph_c = new DirectedWeightedGraph_c(null, null);
    gui canvas;
    gui panel;
    JFrame frame;
    public int brushSize = 10;
    int mouseX = -1;
    int mouseY = -1;
    private boolean mousePressed = false;

    public Menu(String j) {
        super();
        graph_c.load(j);
        canvas = new gui(graph_c);
        frame = new JFrame("My Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.pack();
        frame.add(canvas);
        JMenuBar mb = new JMenuBar();
        menu = new JMenu("file");
        menu1 = new JMenu("function");
        menu2 = new JMenu("Graph");
        tsp = new JMenuItem("tsp");
        center = new JMenuItem("center");
        shortestPath = new JMenuItem("shortestPath");
        isConnected = new JMenuItem("isConnected");
        load = new JMenuItem("load");
        save = new JMenuItem("save");
        addnode = new JMenuItem("addnode");
        connect = new JMenuItem("connect");
        removeNode = new JMenuItem("removeNode");
        removeEdge = new JMenuItem("removeEdge");
        menu1.add(tsp);
        menu1.add(center);
        menu1.add(shortestPath);
        menu1.add(isConnected);
        menu2.add(addnode);
        menu2.add(connect);
        menu2.add(removeNode);
        menu2.add(removeEdge);
        menu.add(load);
        menu.add(save);
        tsp.addActionListener(this);
        center.addActionListener(this);
        shortestPath.addActionListener(this);
        isConnected.addActionListener(this);
        load.addActionListener(this);
        save.addActionListener(this);
        addnode.addActionListener(this);
        connect.addActionListener(this);
        removeNode.addActionListener(this);
        removeEdge.addActionListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        mb.add(menu);
        mb.add(menu1);
        mb.add(menu2);
        frame.setJMenuBar(mb);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load) {
            JFileChooser fc = new JFileChooser();
            int i = fc.showOpenDialog(this.canvas);
            if (i == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                String filepath = f.getPath();
                graph_c.load(filepath);
                panel = new gui(graph_c);
                frame.remove(canvas);
                frame.add(panel);
                frame.repaint();
            }
        }
        if (e.getSource() == save) {
            try{
                JFileChooser j = new JFileChooser(new File("C:\\Users\\pc\\Documents\\New folder\\"));
                //Open the save dialog
                j.showSaveDialog(this);
                File json = j.getSelectedFile();
                Path filepath = json.toPath();
                graph_c.save("filepath");
                System.out.println("you save a file");
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        if (e.getSource() == tsp) {
            algo.init(graph_c);
            String answer = JOptionPane.showInputDialog(this, "please input the number of the cities:");
            List<NodeData> input = new ArrayList<NodeData>();
            String[] split = answer.split(" ");
            for (String i : split) {
                input.add(algo.getGraph().getNode(Integer.parseInt(i)));
            }
            List<NodeData> ans = algo.tsp(input);
            String cities = "";
            for (int j = 0; j < ans.size(); j++) {
                cities += ans.get(j).getKey()+ " ";
            }
            JOptionPane.showMessageDialog(this, "the nodes in the tsp are:" + cities);
            System.out.println("you tsp a file");
        }

        if (e.getSource() == center) {
            algo.init(graph_c);
            if (algo.center() != null) {
                NodeData ans = algo.center();
                JOptionPane.showMessageDialog(this, "The center is node:" + ans.getKey());
            }
            System.out.println("you activate center");
        }
        if (e.getSource() == shortestPath) {
            algo.init(graph_c);
            String answer = JOptionPane.showInputDialog(this, "please input source and destination:");
            String[] arr = answer.split(" ");
            if (algo.shortestPathDist(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])) != 0) {
                double path = algo.shortestPathDist(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
                JOptionPane.showMessageDialog(this, "The shortestpath is :" + path);
            }
            System.out.println("you check the shortestPath");
        }
        if (e.getSource() == isConnected) {
            algo.init(graph_c);
            if (algo.isConnected() == true) {
                JOptionPane.showMessageDialog(this, "The graph is connected");
            } else {
                JOptionPane.showMessageDialog(this, "The graph is not connected");
            }

            System.out.println("you check isConnected");
        }
        if (e.getSource() == addnode) {

//            String answer = JOptionPane.showInputDialog(this, "please input the point to add:");
//            String[] arr = answer.split(" ");
//            geoLocation g1  = new geoLocation(Double.parseDouble(arr[0]), Double.parseDouble(arr[1]),0);
//                Node n1 = new Node(g1, 0, "",0,graph_c.nodeSize()+1);
//                graph_c.addNode(n1);
//                frame.repaint();
            System.out.println("addnode");

        }
        if (e.getSource() == connect) {
            System.out.println("connect");

        }
        if (e.getSource() == removeNode) {
            System.out.println("removeNode");

        }
        if (e.getSource() == removeEdge) {
            System.out.println("removeEdge");

        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {
            this.mousePressed = true;
            this.mouseX = e.getX();
            this.mouseY = e.getY();
             Point p1  =e.getPoint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}







