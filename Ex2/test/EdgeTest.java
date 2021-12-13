import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {
    static geoLocation l1 = new geoLocation(1,2,0);
    static geoLocation l2 = new geoLocation(2,1,0);
    static Node n1 = new Node(l1,3.0,"",0,1);
    static Node n2 = new Node(l2,4.0,"",0,2);
    static Edge e = new Edge(n1,n2,7,"",0);

    @Test
    void getSrc() {
        assertEquals(e.getSrc(),1);
    }

    @Test
    void getDest() {
        assertEquals(e.getDest(),2);
    }

    @Test
    void getWeight() {
        assertEquals(e.getWeight(),7);
    }

    @Test
    void getInfo() {
        assertEquals(e.getInfo(),"");
    }

    @Test
    void setInfo() {
        e.setInfo("shira");
        assertEquals(e.getInfo(),"shira");
    }

    @Test
    void getTag() {
        assertEquals(e.getTag(),0);
    }

    @Test
    void setTag() {
        e.setTag(1);
        assertEquals(e.getTag(),1);
    }
}