package gameClient;

import Server.game_service;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.node_data;
import utils.StdDraw;

import java.sql.Time;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class MyGameGUI {
    private class MyThread extends TimerTask{

        @Override
        public void run() {
            StdDraw.clear();
            printnodes();
            printfruits();
            printrobots();
            StdDraw.show();
        }
    }
    private DGraph graph;
    private game_service game;
    private double maxX,minX,maxY,minY;

    public MyGameGUI(DGraph graph, game_service game){
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(1200,800);
        this.game = game;
        this.graph = graph;
        System.out.println("here");
        paintGraph();
        Timer t = new Timer();
        t.schedule(new MyThread(),10,1);

    }
    private void paintGraph(){

        node_data n1 = graph.getNode(0);
        node_data n2 = graph.getNode(1);
        minX = Math.min(n1.getLocation().x(),n2.getLocation().x());
        maxX = Math.max(n1.getLocation().x(),n2.getLocation().x());
        minY = Math.min(n1.getLocation().y(),n2.getLocation().y());
        maxY = Math.max(n1.getLocation().y(),n2.getLocation().y());

        for (node_data n : graph.getV()) {
            maxX = Math.max(n.getLocation().x(), maxX);
            minX = Math.min(n.getLocation().x(), minX);
            maxY = Math.max(n.getLocation().y(), maxY);
            minY = Math.min(n.getLocation().y(), minY);
        }
        StdDraw.setXscale(minX-0.002,maxX+0.002);
        StdDraw.setYscale(minY-0.002,maxY+0.002);
        printnodes();
        printfruits();






    }
    private void printnodes(){
        for (node_data n:graph.getV()){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.03);
            StdDraw.point(n.getLocation().x(),n.getLocation().y());
            for(edge_data e:graph.getE(n.getKey())){
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenRadius(0.003);
                StdDraw.line(n.getLocation().x(),n.getLocation().y(),graph.getNode(e.getDest()).getLocation().x(),graph.getNode(e.getDest()).getLocation().y());
            }
        }

    }
    public void printfruits(){
        allFruits fruits= new allFruits(graph,game);
        for (Iterator<Fruit> it = fruits.getArrayFruit(); it.hasNext(); ) {
            Fruit f = it.next();
            StdDraw.setPenColor(StdDraw.PINK);
            StdDraw.setPenRadius(0.06);
            StdDraw.point(f.getLocation().x(),f.getLocation().y());
        }
    }
    private void printrobots(){
        allRobots robots= new allRobots(graph,game);
        for (Iterator<Robot> it = robots.getRobots(); it.hasNext(); ) {
            Robot r = it.next();
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(0.09);
            StdDraw.point(r.getlocation().x(),r.getlocation().y());


        }

    }



}
