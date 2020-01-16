package gameClient;

import Server.game_service;
import dataStructure.DGraph;
import dataStructure.node_data;
import utils.StdDraw;

import java.util.Iterator;

public class MyGameGUI {
    private DGraph graph;
    private game_service game;
    private double maxX = Double.MIN_VALUE,minX=Double.MAX_VALUE,maxY=Double.MIN_VALUE,minY=Double.MAX_VALUE;

    public MyGameGUI(DGraph graph, game_service game){
        this.game = game;
        this.graph = graph;

        paintGraph();
    }
    private void paintGraph(){
        node_data n1 = graph.getNode(0);
        node_data n2 = graph.getNode(1);
        minX = n1.getLocation().x();
        maxX = n2.getLocation().x();
        minY = n1.getLocation().y();
        maxY = n2.getLocation().y();
        for (node_data n : graph.getV()) {
            StdDraw.setXscale(minX,maxX);
            StdDraw.setXscale(minY,maxY);
            maxX = Math.max(n.getLocation().x(), maxX);
            minX = Math.min(n.getLocation().x(), minX);
            maxY = Math.max(n.getLocation().y(), maxY);
            minY = Math.min(n.getLocation().y(), minY);
            StdDraw.setPenColor(StdDraw.BOOK_RED);
            StdDraw.setPenRadius(0.03);
            StdDraw.point(n.getLocation().x(), n.getLocation().y());
        }



    }



}
