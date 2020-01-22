package gameClient;

import Server.game_service;
import dataStructure.DGraph;
import org.json.JSONObject;
import utils.Point3D;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

 public class allRobots {
     private Hashtable<Integer, Robot> RobotsHashtable = new Hashtable<>();
     private DGraph graph;

     public Iterator<Robot> getRobots() {
         return RobotsHashtable.values().iterator();
     }
     public Hashtable<Integer,Robot> getR(){
         return this.RobotsHashtable;
     }
     public int getSizeRobots() {
         return RobotsHashtable.values().size();
     }
     public Robot getById(int id) {
         if (RobotsHashtable.size() <= id) {
             return null;
         }
         return RobotsHashtable.get(id);
     }

     public allRobots(DGraph g, game_service game) {
         this.graph=g;
         Iterator<String> r_iterator = game.getRobots().iterator();
         while (r_iterator.hasNext()) {
             String robotJson = r_iterator.next();
             try {
                 JSONObject line = new JSONObject((robotJson));
                 JSONObject ttt = line.getJSONObject("Robot");
                 int rid = ttt.getInt("id");
                 int src = ttt.getInt("src");
                 int dest = ttt.getInt("dest");
                 int speed=ttt.getInt("speed");
                 Point3D possition = new Point3D(ttt.getString("pos"));
                 Robot temp = new Robot(rid, possition,speed);
                 RobotsHashtable.put(temp.getid(),temp);


             } catch (Exception e) {

             }

         }


     }

     }


