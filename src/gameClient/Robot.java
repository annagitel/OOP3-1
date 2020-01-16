package gameClient;
import dataStructure.node_data;
import utils.Point3D;

public class Robot {
    private int id;
    private Point3D location;

    public Robot(int id, Point3D l){
        this.id=id;
        this.location=l;
    }

    public int getid(){
        return this.id;
    }

    public Point3D getlocation(){
        return this.location;
    }

}

