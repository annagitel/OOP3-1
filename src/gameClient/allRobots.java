package gameClient;

import Server.game_service;
import dataStructure.DGraph;
import org.json.JSONObject;
import utils.Point3D;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

 public class allRobots {
     private Hashtable<Integer, Robot> RobotsHashtable = new Hashtable<>();

     public Iterator<Robot> getRobots() {
         return RobotsHashtable.values().iterator();
     }

     public Robot getById(int id) {
         if (RobotsHashtable.size() <= id) {
             return null;
         }
         return RobotsHashtable.get(id);
     }

     public allRobots(DGraph g, game_service game) {
         int id = 0;
         Iterator<String> r_iterator = game.getRobots().iterator();
         while (r_iterator.hasNext()) {
             String robotJson = r_iterator.next();
             try {
                 JSONObject line = new JSONObject((robotJson));
                 JSONObject ttt = line.getJSONObject("Robot");
                 int rid = ttt.getInt("id");
                 int src = ttt.getInt("src");
                 int dest = ttt.getInt("dest");
                 ;
                 Point3D possition = new Point3D(ttt.getString("pos"));
                 Robot temp = new Robot(rid, possition);
                 id++;

             } catch (Exception e) {

             }

         }


     }
 }

