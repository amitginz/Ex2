import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    static geoLocation l1 = new geoLocation(1,2,0);
    static geoLocation l2 = new geoLocation(2,1,0);
    static Node n1 = new Node(l1,3.0,"",0,1);
    static Node n2 = new Node(l2,4.0,"",0,2);

    @org.junit.jupiter.api.Test
    void getKey() {
        assertEquals(n1.getKey(),1);
        assertEquals(n2.getKey(),2);
    }

    @org.junit.jupiter.api.Test
    void getLocation() {
        assertEquals(n1.getLocation(),l1);
    }

    @org.junit.jupiter.api.Test
    void setLocation() {
        geoLocation g = new geoLocation(1,1,1);
        Node n = new Node(null,0,"null",0,0);
        n.setLocation(g);
        assertEquals(n.getLocation(),g);
    }

    @org.junit.jupiter.api.Test
    void getWeight() {
        assertEquals(n1.getWeight(),3.0);
        assertEquals(n2.getWeight(),4.0);
    }

    @org.junit.jupiter.api.Test
    void setWeight() {
        Node n = new Node(null,0,"null",0,0);
        n.setWeight(23);
        assertEquals(n.getWeight(),23.0);
    }

    @org.junit.jupiter.api.Test
    void getInfo() {
        assertEquals(n1.getInfo(),"");
    }

    @org.junit.jupiter.api.Test
    void setInfo() {
        n1.setInfo("shira");
        assertEquals(n1.getInfo(),"shira");
    }

    @org.junit.jupiter.api.Test
    void getTag() {
        assertEquals(n1.getTag(),0);
    }

    @org.junit.jupiter.api.Test
    void setTag() {
        n1.setTag(1);
        assertEquals(n1.getTag(),1);
    }


}