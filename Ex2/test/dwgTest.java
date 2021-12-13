import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class dwgTest {
    HashMap<Integer, NodeData> nodes= new HashMap<>();
    HashMap<Point2D, EdgeData> edges= new HashMap<>();
    DirectedWeightedGraph_c graph = new DirectedWeightedGraph_c(nodes,edges);

     geoLocation l1 = new geoLocation(1,2,0);
     geoLocation l2 = new geoLocation(2,1,0);
     geoLocation l3 = new geoLocation(3,4,0);
     geoLocation l4 = new geoLocation(5,6,0);
     Node n1 = new Node(l1,3.0,"",0,1);
     Node n2 = new Node(l2,4.0,"",0,2);
     Node n3 = new Node(l3,5.0,"",0,3);
     Node n4 = new Node(l4,6.0,"",0,4);
     Edge e1 = new Edge(n1,n2,7,"",0);
     Edge e2 = new Edge(n3,n4,12,"",0);



    @Test
    void getNode() {
        graph.addNode(n1);
        assertEquals(n1,graph.getNode(1));
//        (graph.getNode(2)).equals(new Node(2,new geoLocation(5,1,8), 0, "", 0));
//        (graph.getNode(0)).equals(NodeTest.n1);
    }

    @Test
    void getEdge() {
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        Edge e1 = new Edge(n1, n2, 4, "",0);
        Edge e2 = new Edge(n4, n3, 7, "",0);
        graph.connect(n1.getKey(),n2.getKey(),4);
        graph.connect(n2.getKey(),n3.getKey(),3);
        graph.connect(n4.getKey(),n3.getKey(),6);
        Edge e1a = (Edge) graph.getEdge(n1.getKey(),n2.getKey());
        Edge e2a = (Edge) graph.getEdge(n4.getKey(),n3.getKey());
        e1.equals(e1a);
        e2.equals(e2a);
    }

    @Test
    void addNode() {
        graph.addNode(n1);
        assertEquals(n1,graph.getNode(1));

    }

    @Test
    void connect() {
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        Edge e1 = new Edge(n1, n2, 4, "",0);
        Edge e2 = new Edge(n4, n3, 7, "",0);
        graph.connect(n1.getKey(),n2.getKey(),4);
        graph.connect(n2.getKey(),n3.getKey(),3);
        graph.connect(n4.getKey(),n3.getKey(),6);
        Edge e1a = (Edge) graph.getEdge(n1.getKey(),n2.getKey());
        Edge e2a = (Edge) graph.getEdge(n4.getKey(),n3.getKey());
        e1.equals(e1a);
        e2.equals(e2a);
    }

    @Test
    void nodeIter() {
        Iterator<NodeData> it = graph.nodeIter();
        int i = 0;
        while (it.hasNext()) {
            NodeData n = it.next();
            assertEquals(n.getKey(), graph.getNodes().get(i).getKey());
            assertEquals(n.getInfo(), graph.getNodes().get(i).getInfo());
            assertEquals(n.getLocation(), graph.getNodes().get(i).getLocation());
            assertEquals(n.getTag(), graph.getNodes().get(i).getTag());
            i++;
        }
    }

    @Test
    void edgeIter() {
            Iterator<EdgeData> it = graph.edgeIter();
            int i = 0;
            while (it.hasNext()) {
                    EdgeData e = it.next();
                    Point2D key = new Point(i, e.getDest());
                    assertEquals(e.getWeight(), graph.getEdges().get(key).getWeight());
                    assertEquals(e.getSrc(), graph.getEdges().get(key).getSrc());
                    assertEquals(e.getDest(), graph.getEdges().get(key).getDest());
                    assertEquals(e.getInfo(), graph.getEdges().get(key).getInfo());
                    assertEquals(e.getTag(), graph.getEdges().get(key).getTag());
                    i++;
            }
    }

    @Test
    void testEdgeIter() {
        Iterator<EdgeData> it = graph.edgeIter(0);
        int i = 0;
        while (it.hasNext()) {
            EdgeData e = it.next();
            assertEquals(e.getSrc(), 0);
            assertEquals(e.getDest(), graph.getEdge(0,e.getDest()).getDest());
            assertEquals(e.getInfo(), graph.getEdge(e.getSrc(),e.getDest()).getInfo());
            assertEquals(e.getWeight(), graph.getEdge(e.getSrc(),e.getDest()).getWeight());
            assertEquals(e.getTag(), graph.getEdge(e.getSrc(),e.getDest()).getTag());
        }
    }


    @Test
    void removeNode() {
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(n1.getKey(),n2.getKey(),4);
        graph.connect(n2.getKey(),n3.getKey(),3);
        graph.connect(n4.getKey(),n3.getKey(),6);
        graph.removeNode(2);
        assertNull(graph.getNode(2));
        assertNull(graph.removeNode(2));
    }

    @Test
    void removeEdge() {
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(n1.getKey(),n2.getKey(),4);
        graph.connect(n2.getKey(),n3.getKey(),3);
        graph.connect(n4.getKey(),n3.getKey(),6);
        graph.removeEdge(1,2);
        assertNull(graph.getEdge(1,2));
    }

    @Test
    void nodeSize() {
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        assertEquals(graph.nodeSize(),4);
    }

    @Test
    void edgeSize() {
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(n1.getKey(),n2.getKey(),4);
        graph.connect(n2.getKey(),n3.getKey(),3);
        graph.connect(n4.getKey(),n3.getKey(),6);
        assertEquals(graph.edgeSize(),3);
    }

    @Test
    void getMC() {
         DirectedWeightedGraph_c graph1 = new DirectedWeightedGraph_c(nodes,edges);
         assertEquals(graph1.getMC(),0);
         graph.addNode(n1);
         graph.addNode(n2);
         graph.addNode(n3);
         graph.addNode(n4);
         graph.connect(n1.getKey(),n2.getKey(),4);
         graph.connect(n2.getKey(),n3.getKey(),3);
         graph.connect(n4.getKey(),n3.getKey(),6);
         assertEquals(graph.getMC(),7);
    }
}