import api.*;

public class Node implements NodeData {
    private int Key;
    private double weight;
    private String info;
    private int tag;
    private GeoLocation l;

    public Node(geoLocation location, double weight, String info, int tag, int key){
        this.l = location;
        this.weight = weight;
        this.Key = key;
        this.tag = tag;
        this.info = info;
    }
    @Override
    public int getKey() {
        return this.Key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.l;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.l = p;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
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
