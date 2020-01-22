package Tests;

import dataStructure.DGraph;
import dataStructure.EdgeData;
import gameClient.Fruit;
import org.junit.jupiter.api.Test;
import utils.Point3D;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class fruitTest {
    static DGraph graph = new DGraph();


    @Test
    void findEdgeTest(){
        //???????????????
    }

    @Test
    void getTypeTest(){
        Point3D p = new Point3D("5,7,9");
        EdgeData e = new EdgeData(5,7,10);
        Fruit f = new Fruit(5.3, p, e, -1, 4);
        assertEquals(-1, f.getType());
    }

    @Test
    void getLocationTest(){
        Point3D p = new Point3D("5,7,9");
        EdgeData e = new EdgeData(5,7,10);
        Fruit f = new Fruit(5.3, p, e, -1, 4);
        assertEquals(p.toString(), f.getLocation().toString());
    }

    @Test
    void getValueTest(){
        Point3D p = new Point3D("5,7,9");
        EdgeData e = new EdgeData(5,7,10);
        Fruit f = new Fruit(5.3, p, e, -1, 4);
        assertEquals(5.3, f.getValue());
    }

    @Test
    void getEdgeTest(){
        Point3D p = new Point3D("5,7,9");
        EdgeData e = new EdgeData(5,7,10);
        Fruit f = new Fruit(5.3, p, e, -1, 4);
        assertEquals(e.toString(), f.getEdge().toString());
    }
}
