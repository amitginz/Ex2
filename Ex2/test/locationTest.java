import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class locationTest {

    static geoLocation l1 = new geoLocation(1,2,0);
    static geoLocation l2 = new geoLocation(2,1,0);

    @Test
    void x() {
        assertEquals(l1.x(),1);
        assertEquals(l2.x(),2);
    }

    @Test
    void y() {
        assertEquals(l1.y(),2);
        assertEquals(l2.y(),1);
    }

    @Test
    void z() {
        assertEquals(l1.z(),0);
        assertEquals(l2.z(),0);
    }

//    @Test
//    void setX() {
//        l1.setX(0);
//        l2.setX(1);
//        assertEquals(l1.x(),0);
//        assertEquals(l2.x(),1);
//    }
//
//    @Test
//    void setY() {
//        l1.setY(0);
//        l2.setY(1);
//        assertEquals(l1.y(),0);
//        assertEquals(l2.y(),1);
//    }
//
//    @Test
//    void setZ() {
//        l1.setZ(0);
//        l2.setZ(1);
//        assertEquals(l1.z(),0);
//        assertEquals(l2.z(),1);
//    }

    @Test
    void distance() {
        assertEquals(l1.distance(l2), 1.4142135623730951);
        assertEquals(l1.distance(new geoLocation(5,6,0)), 5.656854249492381);

    }
}