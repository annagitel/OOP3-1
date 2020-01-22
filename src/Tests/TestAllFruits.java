package Tests;
import static org.junit.jupiter.api.Assertions.*;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.edge_data;
import gameClient.Fruit;
import gameClient.allFruits;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import utils.Point3D;

import java.util.ArrayList;

public class TestAllFruits {
    public DGraph graph;
    static String JSONSTRING ="{\"Fruit\":{\"value\":8,\"type\":-1,\"pos\":\"35.4,32.3,0.0\"}}";

    @Test
    void getArrayFruit(){
        game_service game = Game_Server.getServer(0); // you have [0,23] games
        String g = game.getGraph();
        DGraph gg = new DGraph();
        gg.init(g);

        allFruits fruits = new allFruits(gg,game);
        assertNotNull(fruits.getArrayFruit());

        game.stopGame();
    }
    @Test
    void checdi(){
        Point3D p=new Point3D(1,1,0);
        NodeData n =new NodeData(1,p,10);
        Point3D p1= new Point3D(5,5,10);
        NodeData n1= new NodeData(2,p1,6);
        EdgeData e= new EdgeData(1,5,7);
        allFruits fruits= new allFruits();


    }
    @Test
    void finde(){
        game_service game = Game_Server.getServer(0); // you have [0,23] games
        String g = game.getGraph();
        DGraph gg = new DGraph();
        gg.init(g);


    }


}

