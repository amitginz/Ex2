import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class dwgaTest {
     HashMap<Integer, NodeData> nodes= new HashMap<>();
    HashMap<Point2D, EdgeData> edges= new HashMap<>();
    DirectedWeightedGraphAlgorithms_c graphalgo = new DirectedWeightedGraphAlgorithms_c(null);
    DirectedWeightedGraph_c graph = new DirectedWeightedGraph_c(nodes,edges);
    @Test
    void getGraph() {
        graphalgo.init(graph);
        assertEquals(graphalgo.getGraph(),graph);
    }
    @Test
    void init() {
        graphalgo.init(graph);
        assertEquals(graphalgo.getGraph(),graph);
    }
    @Test
    void copy() {
        graphalgo.init(graph);
        Node n0 = new Node(locationTest.l1, 1, "",3,0);
        Node n1 = new Node(locationTest.l2, 2, "",3,0);
        Node n2 = new Node(new geoLocation(5,1,8),2, "", 0, 0);
        Node n3 = new Node(new geoLocation(5,6,6),3, "", 0, 1);
        graphalgo.getGraph().addNode(n0);
        graphalgo.getGraph().addNode(n1);
        graphalgo.getGraph().addNode(n2);
        graphalgo.getGraph().addNode(n3);
        graphalgo.getGraph().connect(n0.getKey(),n1.getKey(),4);
        graphalgo.getGraph().connect(n1.getKey(),n2.getKey(),3);
        graphalgo.getGraph().connect(n2.getKey(),n3.getKey(),6);
        graphalgo.getGraph().connect(n3.getKey(),n0.getKey(),6);
        DirectedWeightedGraph_c gr2 = (DirectedWeightedGraph_c) graphalgo.copy();
        graphalgo.getGraph().equals(gr2);

    }

    @Test
    void isConnected() {
        graphalgo.init(graph);
        Node n0 = new Node(locationTest.l1, 1, "",3,0);
        Node n1 = new Node(locationTest.l2, 2, "",3,1);
        Node n2 = new Node(new geoLocation(5,1,8),2, "", 0, 2);
        Node n3 = new Node(new geoLocation(5,6,6),3, "", 0, 3);
        graphalgo.getGraph().addNode(n0);
        graphalgo.getGraph().addNode(n1);
        graphalgo.getGraph().addNode(n2);
        graphalgo.getGraph().addNode(n3);
        graphalgo.getGraph().connect(n0.getKey(),n1.getKey(),4);
        graphalgo.getGraph().connect(n1.getKey(),n2.getKey(),3);
        graphalgo.getGraph().connect(n2.getKey(),n3.getKey(),6);
        graphalgo.getGraph().connect(n3.getKey(),n0.getKey(),6);
        boolean isConnected0 =  graphalgo.isConnected();
        assertEquals(isConnected0,true);
        graphalgo.getGraph().removeEdge(2,3);
        boolean isConnected1 =  graphalgo.isConnected();
        assertEquals(isConnected1,false);
    }

    @Test
    void shortestPathDist() {
        graphalgo.init(graph);
        Node n0 = new Node(locationTest.l1, 1, "",3,0);
        Node n1 = new Node(locationTest.l2, 2, "",3,1);
        Node n2 = new Node(new geoLocation(5,1,8),2, "", 0, 2);
        Node n3 = new Node(new geoLocation(5,6,6),3, "", 0, 3);
        graphalgo.getGraph().addNode(n0);
        graphalgo.getGraph().addNode(n1);
        graphalgo.getGraph().addNode(n2);
        graphalgo.getGraph().addNode(n3);
        graphalgo.getGraph().connect(n0.getKey(),n1.getKey(),4);
        graphalgo.getGraph().connect(n1.getKey(),n2.getKey(),3);
        graphalgo.getGraph().connect(n2.getKey(),n3.getKey(),6);
        graphalgo.getGraph().connect(n3.getKey(),n0.getKey(),6);
        assertEquals(graphalgo.shortestPathDist(0,3),13);
        assertEquals(graphalgo.shortestPathDist(0,1),4);
        assertEquals(graphalgo.shortestPathDist(0,2),7);

    }

    @Test
    void shortestPath() {
        graphalgo.init(graph);
        Node n0 = new Node(locationTest.l1, 1, "",3,0);
        Node n1 = new Node(locationTest.l2, 2, "",3,1);
        Node n2 = new Node(new geoLocation(5,1,8),2, "", 0, 2);
        Node n3 = new Node(new geoLocation(5,6,6),3, "", 0, 3);
        graphalgo.getGraph().addNode(n0);
        graphalgo.getGraph().addNode(n1);
        graphalgo.getGraph().addNode(n2);
        graphalgo.getGraph().addNode(n3);
        graphalgo.getGraph().connect(n0.getKey(),n1.getKey(),4);
        graphalgo.getGraph().connect(n1.getKey(),n2.getKey(),3);
        graphalgo.getGraph().connect(n2.getKey(),n3.getKey(),6);
        graphalgo.getGraph().connect(n3.getKey(),n0.getKey(),6);
        List<NodeData> l1 = new LinkedList<>();
        l1.add(0,graphalgo.getGraph().getNode(0));
        l1.add(1,graphalgo.getGraph().getNode(1));
        l1.add(2,graphalgo.getGraph().getNode(2));
        assertEquals(graphalgo.shortestPath(0,2), l1);
        List<NodeData> l2 = new LinkedList<>();
        l2.add(graphalgo.getGraph().getNode(1));
        l2.add(graphalgo.getGraph().getNode(2));
        assertEquals(graphalgo.shortestPath(1,3), l2);
    }

    @Test
    void center() {
        graphalgo.init(graph);
        Node n0 = new Node(locationTest.l1, 1, "",3,0);
        Node n1 = new Node(locationTest.l2, 2, "",3,1);
        Node n2 = new Node(new geoLocation(5,1,8),2, "", 0, 2);
        Node n3 = new Node(new geoLocation(5,6,6),3, "", 0, 3);
        graphalgo.getGraph().addNode(n0);
        graphalgo.getGraph().addNode(n1);
        graphalgo.getGraph().connect(n0.getKey(),n1.getKey(),4);
        graphalgo.getGraph().connect(n1.getKey(),n0.getKey(),3);
        assertEquals(graphalgo.center(), n0);
        graphalgo.getGraph().removeNode(0);
        graphalgo.getGraph().removeNode(1);
        graphalgo.getGraph().addNode(n0);
        graphalgo.getGraph().addNode(n1);
        graphalgo.getGraph().addNode(n2);
        graphalgo.getGraph().addNode(n3);
        graphalgo.getGraph().connect(0,1,1);
        graphalgo.getGraph().connect(1,2,7);
        graphalgo.getGraph().connect(2,3,6);
        graphalgo.getGraph().connect(3,1,5);
        graphalgo.getGraph().connect(2,0,2);
        assertEquals(graphalgo.center(), n0);

    }

    @Test
    void tsp() {
        graphalgo.init(graph);
        Node n0 = new Node(locationTest.l1, 1, "",3,0);
        Node n1 = new Node(locationTest.l2, 2, "",3,1);
        Node n2 = new Node(new geoLocation(5,1,8),2, "", 0, 2);
        Node n3 = new Node(new geoLocation(5,6,6),3, "", 0, 3);
        graphalgo.getGraph().addNode(n0);
        graphalgo.getGraph().addNode(n1);
        graphalgo.getGraph().addNode(n2);
        graphalgo.getGraph().addNode(n3);
        graphalgo.getGraph().connect(0,1,1);
        graphalgo.getGraph().connect(1,2,7);
        graphalgo.getGraph().connect(2,3,6);
        graphalgo.getGraph().connect(3,1,5);
        graphalgo.getGraph().connect(2,0,2);
        List<NodeData> vertex = new LinkedList<>();
        vertex.add(n0);
        vertex.add(n1);
        vertex.add(n2);
        vertex.add(n3);
        List<NodeData> ans = new LinkedList<>();
        ans.add(n0);
        ans.add(n1);
        ans.add(n2);
        ans.add(n3);
        assertEquals(graphalgo.tsp(vertex),ans);


    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}
