import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.google.gson.Gson;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Iterator;

public class DirectedWeightedGraph_c implements api.DirectedWeightedGraph {
    private DirectedWeightedGraph graph;
    private HashMap<Integer,NodeData> nodes= new HashMap<>();
    private HashMap<Point2D,EdgeData> edges= new HashMap<>();
    private int mc =0;

    public DirectedWeightedGraph_c(HashMap nodes, HashMap edges){
        this.edges = edges;
        this.nodes = nodes;

    }
    @Override
    public NodeData getNode(int key) {
        return this.nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest){
        Point2D p =new Point(src,dest);
        if(edges.get(p)!=null)
            return edges.get(p);
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        this.nodes.put(n.getKey(),n);
        mc++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        Point2D p = new Point(src,dest);
        NodeData n1 = getNode(src);
        NodeData n2 = getNode(dest);
        Edge e = new Edge(n1, n2, w, "", 0);
        this.edges.put(p, e);
        mc++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {  //Todo: add Exception
        return new Iterator<NodeData>() {
            Iterator<NodeData> iter =nodes.values().iterator();
            private int N_ITERMC=mc;
            @Override
            public boolean hasNext() {
                if(N_ITERMC!=mc){
                    throw new NoSuchElementException();
                }
                return iter.hasNext();
            }

            @Override
            public NodeData next() {
                if(N_ITERMC!=mc){
                    throw new NoSuchElementException();
                }
                return iter.next();
            }
        };
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return new Iterator<EdgeData>() {
            Iterator<EdgeData> iter =edges.values().iterator();
            private int N_ITERMC=mc;
            @Override
            public boolean hasNext() {
                if(N_ITERMC!=mc){
                    throw new NoSuchElementException();
                }
                return iter.hasNext();
            }

            @Override
            public EdgeData next() {
                if(N_ITERMC!=mc){
                    throw new NoSuchElementException();
                }
                return iter.next();
            }
        };
    }


    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        Iterator<EdgeData> it_edge = edges.values().iterator();
        for (int i = 0; i < this.edges.size(); i++) {
            if (this.edges.get(i).getSrc() != node_id) {
                if (it_edge.hasNext()) {
                    it_edge.remove();
                }
            }
        }
        return new Iterator<EdgeData>() {
            Iterator<EdgeData> it_edge=edges.values().iterator();
            private int N_ITERMC=mc;
            @Override
            public boolean hasNext() {
                if(N_ITERMC!=mc){
                    throw new NoSuchElementException();
                }
                return it_edge.hasNext();
            }

            @Override
            public EdgeData next() {
                if(N_ITERMC!=mc){
                    throw new NoSuchElementException();
                }
                return it_edge.next();
            }
        };
    }

    @Override
    public NodeData removeNode(int key) {
        if(!edges.isEmpty()){
        for (int i = 0; i <edges.size() ; i++) {
            if(edges.containsKey(key)){
                edges.remove(key);
                mc++;}
        }}
        mc++;
        return this.nodes.remove(key);
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Point2D p = new Point(src,dest);
        mc++;
        return this.edges.remove(p);

    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }


    @Override
    public int getMC() {
        return mc;
    }

    public boolean load(String file) {
        try {
            Object ob = new JSONParser().parse(new FileReader( file));
            if (ob == null)
                throw new Exception("Error");
            JSONObject js = (JSONObject) ob;
            JSONArray edgesArr = (JSONArray) js.get("Edges");
            JSONArray nodesArr = (JSONArray) js.get("Nodes");
            Iterator itEdge = edgesArr.iterator();
            Iterator itNode = nodesArr.iterator();

            Map mapNode;
            HashMap<Integer, NodeData> nodesMap = new HashMap<>();

            while (itNode.hasNext()) {
                mapNode = (Map) itNode.next();
                int id = Integer.parseInt(Objects.toString(mapNode.get("id")));
                String[] pos = ((String) mapNode.get("pos")).split(",");
                double x = Double.parseDouble(pos[0]);
                double y = Double.parseDouble(pos[1]);
                double z = Double.parseDouble(pos[2]);
//                System.out.println(MessageFormat.format("{0}, {1}, {2}, {3}", x, y, z, id));
                geoLocation p1 = new geoLocation(x,y,z);
                Node node = new Node(p1,0,"",id,0);
                nodesMap.put(id,node);
            }

            Map mapEdge;
            HashMap<Point2D, EdgeData> edgesMap = new HashMap<>();

            while (itEdge.hasNext()) {
                mapEdge = (Map) itEdge.next();
                int src = Integer.parseInt(Objects.toString(mapEdge.get("src")));
                int dest = Integer.parseInt(Objects.toString(mapEdge.get("dest")));
                double w = Double.parseDouble(Objects.toString(mapEdge.get("w")));
                Node one = (Node) nodesMap.get(src);
                Node two = (Node) nodesMap.get(dest);
                Edge e = new Edge(one,two,w,"",0);
                Point2D p =new Point(src,dest);
                edgesMap.put(p,e);

            }
            DirectedWeightedGraph graph = new DirectedWeightedGraph_c(nodesMap,edgesMap);
            this.nodes  = nodesMap;
            this.edges  = edgesMap;

        } catch (Exception e) {

            System.out.println(e);
            return false;

        }
        return true;
    }

    public HashMap<Integer, NodeData> getNodes(){
        return this.nodes;
    }
    public HashMap<Point2D, EdgeData> getEdges(){
        return this.edges;
    }

    public boolean save(String file)  {
        try {
            Gson gson = new Gson();
            String json1 = gson.toJson(this.edges);
            String json2 = gson.toJson(this.nodes);
            PrintWriter pw = new PrintWriter(file);
            pw.write(json1);
            pw.append(json2);
            pw.flush();
            pw.close();
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



}
