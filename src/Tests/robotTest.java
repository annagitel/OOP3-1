package Tests;

import gameClient.Robot;
import org.junit.jupiter.api.Test;
import utils.Point3D;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class robotTest {
    @Test
    void  robotTest(){

    }

    void getIdTest(){
        Point3D p = new Point3D("5,7,9");
        Robot r = new Robot(1,p,10);
        assertEquals(1, r.getid());
    }

    void getLocationTest(){
        Point3D p = new Point3D("5,7,9");
        Robot r = new Robot(1,p,10);
        assertEquals(p.toString(), r.getlocation().toString());

    }

    void getSpeedTest(){
        Point3D p = new Point3D("5,7,9");
        Robot r = new Robot(1,p,10);
        assertEquals(10, r.getSpeed());

    }

}
