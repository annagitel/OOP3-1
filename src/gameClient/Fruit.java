package gameClient;
import dataStructure.edge_data;
import utils.Point3D;

public class Fruit {

        private Point3D pos; // location of the fruit
        private double value; // value of the fruit
        private int type; // if -1 you go from big node to small node and 1 if the opposite
        private edge_data edge; // the edge that the fruit is on
        private int fid; // represent the the id of the fruit by its num of creation


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


