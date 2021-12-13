import api.*;
public class Edge implements EdgeData {
    private NodeData src;
    private NodeData dest;
    private double weight;
    private int tag;
    private String info;


    public Edge(NodeData src, NodeData dest , double weight, String info, int tag){
        this.src = src;
        this.dest = dest;
        this.tag = tag;
        this.info = info;
        this.weight = weight;
    }
    @Override
    public int getSrc() {
        return this.src.getKey();
    }

    @Override
    public int getDest() {
        return this.dest.getKey();
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
