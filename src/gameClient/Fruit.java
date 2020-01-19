package gameClient;
import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.edge_data;
import dataStructure.node_data;
import org.json.JSONObject;
import utils.Point3D;

import java.util.Collection;
import java.util.Iterator;

public class Fruit {

        private Point3D pos; // location of the fruit
        private double value; // value of the fruit
        private int type; // if -1 you go from big node to small node and 1 if the opposite
        private edge_data edge; // the edge that the fruit is on
        private int fid; // represent the the id of the fruit by its num of creation

    public Fruit(DGraph g, String jsonString){
        try {
            JSONObject fruit = new JSONObject(jsonString);
            fruit = fruit.getJSONObject("Fruit");
            String loc = fruit.getString("pos");
            Double val = fruit.getDouble("value");
            int type = fruit.getInt("type");

            this.pos = new Point3D(loc);
            this.value = val;
            this.edge = findEdge(g,type);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private EdgeData findEdge(DGraph g, int type) {
        Iterator<node_data> nit = g.getV().iterator();
        while (nit.hasNext()) {
            Collection<edge_data> e = g.getE(nit.next().getKey());
            if (e != null) {
                Iterator<edge_data> eit = e.iterator();
                while (eit.hasNext()) {
                    edge_data current = eit.next();
                    double ps = g.getNode(current.getSrc()).getLocation().distance2D(this.pos);
                    double pd = g.getNode(current.getDest()).getLocation().distance2D(this.pos);
                    double sd = g.getNode(current.getSrc()).getLocation().distance2D(g.getNode(current.getDest()).getLocation());
                    if (ps + pd - sd < 0.0000001) {
                        if ((current.getDest() - current.getSrc()) * type > 0)
                            return (EdgeData) current;
                    }
                }
            }
        }
        return null;
    }

        public Fruit() {
        }

        public Fruit(double v, Point3D p, edge_data e ,int type, int id) {
            this.value = v;
            this.pos = new Point3D(p);
            this.edge = e;
            this.fid=id;
            this.type=type;
        }

        public int getType() {

            return this.type;
        }

        public Point3D getLocation() {
            return this.pos;
        }



        public double getValue() {
            return this.value;
        }

       public edge_data getEdge(){
            return this.edge;
       }
       public int getFid(){
            return this.fid;
       }

    }


