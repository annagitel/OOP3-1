package Tests;
import dataStructure.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class edgeDataTest {
    @Test
    void EdgeDatat(){
        EdgeData e= new EdgeData();
        assertEquals(0,e.getTag());
        int src=3;
        int dest=2;
        double w=7;
        EdgeData y=new EdgeData(src,dest,w);
        assertEquals(3,y.getSrc());
        assertEquals(2,y.getDest());
        assertEquals(7,y.getWeight());
        EdgeData g=new EdgeData(src,dest,w, "blabla",0);
        assertEquals(3,g.getSrc());
        assertEquals(2,g.getDest());
        assertEquals(7,g.getWeight());
        assertEquals("blabla",g.getInfo());
        assertEquals(0,g.getTag());



    }
    @Test
    void getSrc(){
        EdgeData e= new EdgeData(1,4,6,"BLABLA",1);
        assertEquals(1,e.getSrc());
    }
    @Test
    void getDest(){
        EdgeData e= new EdgeData(1,4,6,"BLABLA",1);
        assertEquals(4,e.getDest());
    }

    @Test
    void getWeight(){
        EdgeData e= new EdgeData(1,4,6,"BLABLA",1);
        assertEquals(6,e.getWeight());
    }

    @Test
    void getInfo(){
        EdgeData e= new EdgeData(1,4,6,"BLABLA",1);
        assertEquals("BLABLA",e.getInfo());
    }

    @Test
    void getTag(){
        EdgeData e= new EdgeData(1,4,6,"BLABLA",1);
        assertEquals(1,e.getTag());
    }
    @Test
    void setInfo(){
        EdgeData e= new EdgeData();
        e.setInfo("BLABLA");
        assertEquals("BLABLA",e.getInfo());
    }
    @Test
    void setTag(){
        EdgeData e= new EdgeData();
        e.setTag(3);
        assertEquals(3,e.getTag());
    }

}
