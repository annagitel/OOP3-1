package Tests;
import static org.junit.jupiter.api.Assertions.*;
import Server.game_service;
import Server.Game_Server;
import dataStructure.DGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import gameClient.*;

public class TestAllRobots {
    DGraph g = new DGraph();
    game_service game = Game_Server.getServer(6);
    allRobots r = new allRobots(g,game);

    @Test
    void sizeTest(){

    }

}
