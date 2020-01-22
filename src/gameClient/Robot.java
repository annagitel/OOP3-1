package gameClient;
import dataStructure.node_data;
import org.json.JSONObject;
import utils.Point3D;

public class Robot {
    private int id;
    private Point3D location;
    private int speed;

    public Robot(String jsonString){
        try{
            JSONObject robot = new JSONObject(jsonString);
            int idd = robot.getInt("id");
            String loc = robot.getString("pos");
            int speed= robot.getInt("speed");

            this.id = idd;
            this.location = new Point3D(loc);
            this.speed=speed;

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Robot(int id, Point3D l,int s){
        this.id=id;
        this.location=l;
        this.speed=s;
    }

    public int getid(){
        return this.id;
    }

    public Point3D getlocation(){
        return this.location;
    }

    public int getSpeed(){
        return this.speed;
    }

}

