package gameClient;

import Server.game_service;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.node_data;
import org.json.JSONObject;
import utils.Point3D;

import java.util.ArrayList;
import java.util.Iterator;

public class allFruits {

    private ArrayList<Fruit> fruitArrayList = new ArrayList<>();


   public Iterator<Fruit> getArrayFruit(){  //return all the fruit by iterator
       return fruitArrayList.iterator();
   }
   public Fruit getFByid(int id){ // return fruit by id
       if(id>=fruitArrayList.size())
           return null;
       return fruitArrayList.get(id);
   }

    public allFruits(DGraph g, game_service game){
        int id =0;
        Iterator<String> f_iterator = game.getFruits().iterator();
        while(f_iterator.hasNext()) {
            String fruitJson=f_iterator.next();
            try {
                JSONObject line = new JSONObject((fruitJson));
                JSONObject ttt = line.getJSONObject("Fruit");
                int value = ttt.getInt("value");
                int type = ttt.getInt("type");
                Point3D possition =new Point3D (ttt.getString("pos"));
                Fruit temp = new Fruit(value, possition, findE(possition, g, type), type, id);
                id++;
            }catch (Exception e){

            }
        }
    }
    public edge_data findE(Point3D p, DGraph g, int type){
        for (node_data src:g.getV()) {
            for (edge_data e : g.getE(src.getKey())) {
                if(checkDis(p,src,e,g)){
                    int temp=src.getKey()>e.getDest()?-1:1;
                    if(temp==type){
                        return e;
                    }
                }

            }
        }

        return null;
    }
    private boolean checkDis(Point3D p,node_data s,edge_data e, DGraph g){
       double d_p= p.distance2D(s.getLocation())+p.distance2D(g.getNode(e.getDest()).getLocation());
       double d_s_e=s.getLocation().distance2D(g.getNode(e.getDest()).getLocation());
       if(Math.abs(d_p-d_s_e)<0.0000001){
           return true;
       }
       return false;
    }
}
