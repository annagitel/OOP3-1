package Tests;
import dataStructure.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import utils.Point3D;

public class dgraphTest {
    static graph g = new DGraph();
@Test
    void getnode(){
    Point3D p=new Point3D(1,2,0);
    NodeData n= new NodeData(1,p);
    g.addNode(n);
    assertEquals(n,g.getNode(1));
}
@Test
    void getedge(){
    Point3D p=new Point3D(2,2,0);
    NodeData n= new NodeData(2,p);
    g.addNode(n);
    Point3D p2=new Point3D(3,3,0);
    NodeData n2= new NodeData(3,p2);
    g.addNode(n2);
    g.connect(2,3,4);
    assertEquals(4,g.getEdge(2,3).getWeight());
}
@Test
    void addnode(){
    Point3D p= new Point3D(5,5,0);
    NodeData n= new NodeData(5,p);
    g.addNode(n);
    assertEquals(n,g.getNode(5));
}
@Test
    void connect(){
    Point3D p=new Point3D(1,7,0);
    NodeData n= new NodeData(7,p);
    g.addNode(n);
    Point3D p2=new Point3D(1,6,0);
    NodeData n2= new NodeData(6,p2);
    g.addNode(n2);
    g.connect(6,7,4);
    assertEquals(4,g.getEdge(6,7).getWeight());
}
@Test
    void getv(){
    graph g1= new DGraph();
    Point3D p=new Point3D(2,2,0);
    NodeData n= new NodeData(2,p);
    g1.addNode(n);
    Point3D p2=new Point3D(3,3,0);
    NodeData n2= new NodeData(3,p2);
    g1.addNode(n2);
    assertEquals(2,g1.getV().size());

}
@Test
    void gete(){
    graph g2= new DGraph();
    Point3D p=new Point3D(2,2,0);
    NodeData n= new NodeData(2,p);
    g2.addNode(n);
    Point3D p2=new Point3D(3,3,0);
    NodeData n2= new NodeData(3,p2);
    g2.addNode(n2);
    Point3D p3=new Point3D(1,6,0);
    NodeData n3= new NodeData(6,p2);
    g2.addNode(n3);
    g2.connect(2,3,1);
    g2.connect(2,6,5);
    assertEquals(2,g2.getE(n.getKey()).size());
}
@Test
    void  removenode(){
    graph g2= new DGraph();
    Point3D p=new Point3D(2,2,0);
    NodeData n= new NodeData(2,p);
    g2.addNode(n);
    Point3D p2=new Point3D(3,3,0);
    NodeData n2= new NodeData(3,p2);
    g2.addNode(n2);
    Point3D p3=new Point3D(1,6,0);
    NodeData n3= new NodeData(6,p3);
    g2.addNode(n3);
    g2.removeNode(n.getKey());
    assertEquals(null,g2.getNode(n.getKey()));
}
@Test
    void removedge(){
    graph g2= new DGraph();
    Point3D p=new Point3D(2,2,0);
    NodeData n= new NodeData(2,p);
    g2.addNode(n);
    Point3D p2=new Point3D(3,3,0);
    NodeData n2= new NodeData(3,p2);
    g2.addNode(n2);
    g2.connect(2,3,5);
    g2.removeEdge(2,3);
    assertNull(g2.getEdge(2,3));
}
@Test
    void nodesizet(){
    graph g2=new DGraph();
    Point3D p=new Point3D(2,2,0);
    NodeData n= new NodeData(2,p);
    g2.addNode(n);
    Point3D p2=new Point3D(3,3,0);
    NodeData n2= new NodeData(3,p2);
    g2.addNode(n2);
    Point3D p3=new Point3D(1,6,0);
    NodeData n3= new NodeData(6,p3);
    g2.addNode(n3);
    assertEquals(3,g2.nodeSize());
}
@Test
    void edgesizet(){
    graph g2=new DGraph();
    Point3D p=new Point3D(2,2,0);
    NodeData n= new NodeData(2,p);
    g2.addNode(n);
    Point3D p2=new Point3D(3,3,0);
    NodeData n2= new NodeData(3,p2);
    g2.addNode(n2);
    Point3D p3=new Point3D(1,6,0);
    NodeData n3= new NodeData(6,p3);
    g2.addNode(n3);
    g2.connect(2,3,1);
    g2.connect(3,6,2);
    assertEquals(2,g2.edgeSize());
}
@Test
    void mct(){
    graph g2=new DGraph();
    Point3D p=new Point3D(2,2,0);
    NodeData n= new NodeData(2,p);
    g2.addNode(n);
    Point3D p2=new Point3D(3,3,0);
    NodeData n2= new NodeData(3,p2);
    g2.addNode(n2);
    Point3D p3=new Point3D(1,6,0);
    NodeData n3= new NodeData(6,p3);
    g2.addNode(n3);
    assertEquals(3,g2.getMC());
}
}
