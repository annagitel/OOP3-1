package gameClient;

import Server.game_service;
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.node_data;
import org.json.JSONObject;
import utils.Point3D;

import java.util.ArrayList;
import java.util.Iterator;

public class allFruits {

    private ArrayList<Fruit> fruitArrayList = new ArrayList<>();
    private DGraph graph;

public allFruits(){

}
public ArrayList<Fruit> getArray(){
    return this.fruitArrayList;
}
   public Iterator<Fruit> getArrayFruit(){  //return all the fruit by iterator
       return fruitArrayList.iterator();
   }


    public allFruits(DGraph g, game_service game){
       this.graph = g;
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
                fruitArrayList.add(temp);
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

    public Fruit closeTo(int src) {

       Iterator<Fruit> fruitIterator = getArrayFruit();
        Graph_Algo graph_algo = new Graph_Algo(this.graph);
        System.out.println(fruitArrayList.size());
        if (fruitIterator.hasNext()) {
            Fruit closeTo = fruitIterator.next();
            while (fruitIterator.hasNext()) {
                Fruit current = fruitIterator.next();
                if (graph_algo.shortestPathDist(src, closeTo.getEdge().getSrc()) > graph_algo.shortestPathDist(src, current.getEdge().getSrc())) {
                        closeTo = current;
                }

            }
            return closeTo;
        }
        return null;
    }
    public Fruit theMaxValue(int src){
        ArrayList<Fruit> f= getArray();
        Fruit temp=f.get(0);
        System.out.println("first f"+temp.getValue());
        for (Fruit f1:f) {

            System.out.println("f in lopp"+f1.getValue());
            if(f1.getValue()>temp.getValue()){
                temp=f1;
                System.out.println("change if bigger"+temp.getValue());
            }

        }
        System.out.println("the biggest"+temp.getValue());
        return temp;
    }
}
