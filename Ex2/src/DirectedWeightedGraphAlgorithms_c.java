import api.*;
import api.DirectedWeightedGraph;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.*;
import java.util.List;

public class DirectedWeightedGraphAlgorithms_c implements DirectedWeightedGraphAlgorithms,Cloneable {
    private  DirectedWeightedGraph g;
    final static int INF=999999;

    public DirectedWeightedGraphAlgorithms_c(DirectedWeightedGraph g1)
    {

        this.g=g1;
    }




    @Override
    public void init(DirectedWeightedGraph g) {
        this.g = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.g;
    }


    @Override
    public DirectedWeightedGraph copy()
    {
        int k;
        HashMap<Integer, NodeData> nodeMap = new HashMap<>();
        HashMap<Point2D, EdgeData> edgeMap = new HashMap<>();
        for(int i = 0; i < g.nodeSize(); i++){
            nodeMap.put(i,g.getNode(i));
        }
        for(int j = 0; j < g.edgeSize(); j++){
            if(g.getNode(j) != null){
               for(k = 0; k < g.edgeSize();k++){
                    if(g.getEdge(j,k) == null){
                        continue;
                    }
                    Point2D p = new Point(j,k);
                    edgeMap.put(p,g.getEdge(j,k));
                }
            }
        }
        DirectedWeightedGraph g_copy = new DirectedWeightedGraph_c(nodeMap,edgeMap);
        return g_copy;
    }

    @Override
    public boolean isConnected() {
        double[] answer = shortestPathDist_help(0,g.nodeSize());
        for (int i = 0 ; i < g.nodeSize(); i++){
            if(answer[i] == INF)return false;
        }
        return true;
    }
    @Override
    public double shortestPathDist(int src, int dest) {
        double[] answer = shortestPathDist_help(src,dest);
        return answer[dest];
    }
    public double[] shortestPathDist_help(int src, int dest) {
        PriorityQueue<Integer> pQueue
                = new PriorityQueue<Integer>();
        double[] dist = new double[g.nodeSize()];
        int[] prev = new int[g.nodeSize()];
        for (int i = 0 ; i <g.nodeSize() ;i++){
            dist[i] = INF;
            prev[i] = Integer.MAX_VALUE;
            pQueue.add(i);
        }
        dist[src] = 0;
        int u =0;
        double update_w= 0;
        while (!pQueue.isEmpty()){
            u = pQueue.poll();
            for (int v = 0 ; v <g.nodeSize();v++){
                if(g.getEdge(u,v) == null || pQueue.contains(v) == false){
                    continue;
                }
                update_w = dist[u] + g.getEdge(u,v).getWeight();
                if (update_w < dist[v]) {
                    dist[v] = update_w;
                    prev[v] = u;

                }
            }
        }
        return dist;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        int[] list = shortestPathDist_help1(src, dest);
        List<NodeData> l = new LinkedList<>();
        for(int i = src+1; i <g.nodeSize(); i++){
            l.add(g.getNode(list[i]));
        }
        return l;
    }
    public int[] shortestPathDist_help1(int src, int dest) {
        PriorityQueue<Integer> pQueue
                = new PriorityQueue<Integer>();
        double[] dist = new double[g.nodeSize()];
        int[] prev = new int[g.nodeSize()];
        for (int i = 0 ; i <g.nodeSize() ;i++){
            dist[i] = INF;
            prev[i] = Integer.MAX_VALUE;
            pQueue.add(i);
        }
        dist[src] = 0;
        int u =0;
        double update_w= 0;
        while (!pQueue.isEmpty()){
            u = pQueue.poll();
            for (int v = 0 ; v <g.nodeSize();v++){
                if(g.getEdge(u,v) == null || pQueue.contains(v) == false){
                    continue;
                }
                update_w = dist[u] + g.getEdge(u,v).getWeight();
                if (update_w < dist[v]) {
                    dist[v] = update_w;
                    prev[v] = u;

                }
            }
        }
        return prev;
    }

    @Override
    public NodeData center()
    {
        double[] arr = center_help(0,g.nodeSize());
       List<Double> max_l= new LinkedList<>();
       int max_value = 1;
        for(int i = 0 ; i < g.nodeSize() ; i++) {
            for (int j = 2; j < g.nodeSize(); j++) {
                if(g.getEdge(i,j) == null || g.getEdge(i, max_value) == null){
                    continue;
                }
                if(g.getEdge(i,j).getWeight() > g.getEdge(i,max_value).getWeight()){
                    max_value = j;
                }
                if(j == g.nodeSize()-1){
                    max_l.add(g.getEdge(i,max_value).getWeight());
                }
            }
        }
        int min_value = 0;
        for(int i = 1 ; i <max_l.size(); i++){
            if(max_l.get(i) < max_l.get(min_value)){
                min_value = i;
            }
        }
        NodeData ans = g.getNode(min_value);
        return ans;
    }
    public double[] center_help(int src, int dest) {
        PriorityQueue<Integer> pQueue
                = new PriorityQueue<Integer>(Collections.reverseOrder());
        double[] dist = new double[g.nodeSize()];
        int[] prev = new int[g.nodeSize()];
        for (int i = 0 ; i <g.nodeSize() ;i++){
            dist[i] = INF;
            prev[i] = Integer.MAX_VALUE;
            pQueue.add(i);
        }
        dist[src] = 0;
        int u =0;
        double update_w= 0;
        while (!pQueue.isEmpty()){
            u = pQueue.poll();
            for (int v = 0 ; v <g.nodeSize();v++){
                if(g.getEdge(u,v) == null || pQueue.contains(v) == false){
                    continue;
                }
                update_w = dist[u] + g.getEdge(u,v).getWeight();
                if (update_w > dist[v]) {
                    dist[v] = update_w;
                    prev[v] = u;

                }
            }
        }
        return dist;
    }
    @Override
    public List<NodeData> tsp(List<NodeData> cities)
    {
        List<Integer> help = new ArrayList<>();
        List<NodeData> ans =new ArrayList<>();
        for(int i =0;i<cities.size();i++)
        {
            help.add(cities.get(i).getKey());
        }

        List<NodeData> little;
        NodeData cur = cities.get(0);
        ans.add(this.g.getNode(help.get(0)));
        help.remove(0);
        while (help.size()>=1)
        {
            double shortestDist = Double.MAX_VALUE;
            int d = -1;
            int l = -1;
            for(int i = 0 ; i < help.size();i++)
            {
                int key =help.get(i);
                if(shortestPathDist(cur.getKey(),key) < shortestDist)
                {
                    shortestDist = shortestPathDist(cur.getKey(),key);
                    d = key;
                    l = i;
                }
            }
            little = shortestPath(cur.getKey(),d);
//            little.remove(0);
            while (little.size()>=1)
            {
                if (!(ans.contains(little.get(0))))
                {
                    ans.add(little.get(0));
                }
                little.remove(0);
            }
            int Node_to_get = help.get(l);
            cur = this.g.getNode(Node_to_get);
            help.remove(help.get(l));
            if (help.size()==1 && !ans.contains(g.getNode(d+1)))
                ans.add(g.getNode(d+1));
        }

        return ans;
    }
    @Override
    public boolean save(String file)  {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(g);
            PrintWriter pw = new PrintWriter(file);
            pw.write(json);
            pw.flush();
            pw.close();
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
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
            this.g = graph;

        } catch (Exception e) {

            System.out.println(e);
            return false;

        }
        return true;
    }

  

}

