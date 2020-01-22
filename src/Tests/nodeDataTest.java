package Tests;

import dataStructure.*;
import utils.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class nodeDataTest {

    @Test
    void NodeData(){
        NodeData n=new NodeData();
        assertEquals("false", n.getInfo());
        assertEquals(0,n.getTag());
        int k=1;
        Point3D n1= new Point3D(1,1,0);
        NodeData m=new NodeData(k,n1,7);
        assertEquals(7,m.getWeight());
        assertEquals(1,m.getKey());
        Point3D n2= new Point3D(2,2,0);
        NodeData y= new NodeData(3,n2);
        assertEquals(3,y.getKey());
        Point3D p=new Point3D(1,2,0);
        NodeData a= new NodeData(1,p,7,"blabla",2);
        assertEquals(1,a.getKey());
        assertEquals(p,a.getLocation());
        assertEquals(7,a.getWeight());
        assertEquals("blabla",a.getInfo());
        assertEquals(2,a.getTag());
    }
    @Test
    void getKey(){
        Point3D n1= new Point3D(1,1,0);
        NodeData m=new NodeData(1,n1,7);
        assertEquals(1, m.getKey());
        Point3D n= new Point3D(2,2,0);
        NodeData n2= new NodeData(3,n);
        assertEquals(3, n2.getKey());
    }
    @Test
    void getLocation(){
        Point3D p=new Point3D(1,2,0);
        NodeData n= new NodeData(1,p,7,"blabla",2);
        assertEquals( new Point3D(1,2,0), n.getLocation());

    }
    @Test
    void setLocation(){
        Point3D p=new Point3D(1,2,0);
        NodeData n= new NodeData();
        n.setLocation(p);
        assertEquals( new Point3D(1,2,0), n.getLocation());
    }
    @Test
    void getWeight(){
        Point3D p=new Point3D(1,2,0);
        NodeData n= new NodeData(1,p,7,"blabla",2);
        assertEquals(7,n.getWeight());
        NodeData n2= new NodeData(1,p,6);
        assertEquals(6,n2.getWeight());
    }
    @Test
    void setWeight(){
        Point3D p=new Point3D(1,2,0);
        NodeData n= new NodeData(1,p,7,"blabla",2);
        NodeData m= new NodeData();
        m.setWeight(n.getWeight());
        assertEquals(7, m.getWeight());
    }
    @Test
    void getInfo(){
        Point3D p=new Point3D(1,2,0);
        NodeData n= new NodeData(1,p,7,"blabla",2);
        assertEquals("blabla",n.getInfo());
    }
    @Test
    void setInfo(){
        NodeData n= new NodeData();
        n.setInfo("blabla");
        assertEquals("blabla",n.getInfo());
    }
    @Test
    void getTag(){
        Point3D p=new Point3D(1,2,0);
        NodeData n= new NodeData(1,p,7,"blabla",2);
        assertEquals(2,n.getTag());
        NodeData n2= new NodeData();
        assertEquals(0,n2.getTag());
    }
    @Test
    void setTag(){
        NodeData n= new NodeData();
        n.setTag(2);
        assertEquals(2,n.getTag());
        NodeData n2= new NodeData();
        n2.setTag(n.getTag());
        assertEquals(2,n2.getTag());
    }





}
