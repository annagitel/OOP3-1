package gameClient;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import gameClient.allRobots;
import algorithms.Graph_Algo;
import dataStructure.*;
import org.json.JSONException;
import org.json.JSONObject;

import Server.Game_Server;
import Server.game_service;
import oop_dataStructure.OOP_DGraph;
import oop_dataStructure.oop_edge_data;
import oop_dataStructure.oop_graph;
import utils.Point3D;
import utils.StdDraw;

import javax.swing.*;

/**
 * This class represents a simple example for using the GameServer API:
 * the main file performs the following tasks:
 * 1. Creates a game_service [0,23] (line 36)
 * 2. Constructs the graph from JSON String (lines 37-39)
 * 3. Gets the scenario JSON String (lines 40-41)
 * 4. Prints the fruits data (lines 49-50)
 * 5. Add a set of robots (line 52-53) // note: in general a list of robots should be added
 * 6. Starts game (line 57)
 * 7. Main loop (should be a thread) (lines 59-60)
 * 8. move the robot along the current edge (line 74)
 * 9. direct to the next edge (if on a node) (line 87-88)
 * 10. prints the game results (after "game over"): (line 63)
 *  
 * @author boaz.benmoshe
 *
 */
public class SimpleGameClient extends Component {
	static boolean auto=false;
	public static void main(String[] a) throws Exception {
		test1();}
	public static void test1() throws Exception {
		KML_Logger kmlog;
		JFrame f = new JFrame();
		auto = JOptionPane.showConfirmDialog(
				f,
				"Would you like to play auto game?",
				"The GAME of Yana & Anna",
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION;
		int scenario_num = Integer.parseInt(JOptionPane.showInputDialog(f,"Choose youre game num between [0,23]"));
		game_service game = Game_Server.getServer(scenario_num); // you have [0,23] games
		String g = game.getGraph();
		DGraph gg = new DGraph(); /*****************************************************/
		gg.init(g);
		allFruits allFruits=new allFruits(gg,game);
		allRobots allRobots=new allRobots(gg,game);

		kmlog = new KML_Logger();

		kmlog.addRovotsAndFruits(gg,game);
		MyGameGUI myGameGUI = new MyGameGUI(gg,game);
		String info = game.toString();
		JSONObject line;
		try {
			line = new JSONObject(info);
			JSONObject ttt = line.getJSONObject("GameServer");
			int rs = ttt.getInt("robots");
			if(!auto){
				kmlog.addGraph(gg);
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,
						"you have "+rs+" robots, please posit them");
			}
			System.out.println(info);
			System.out.println(g);
			// the list of fruits should be considered in your solution
			Iterator<String> f_iter = game.getFruits().iterator();
			while(f_iter.hasNext()) {System.out.println(f_iter.next());}
			int src_node = 0;  // arbitrary node, you should start at one of the fruits
			for(int a = 0;a<rs;a++) {
				if(auto) {
					game.addRobot(src_node + a);
				}
				else{
					int where = -1;
					while (where==-1){
						where = putrobots(gg,game,rs);
					}
					try {
						Thread.sleep(200);
					}catch (Exception e){

					}
					game.addRobot(where);
				}
			}
		}
		catch (JSONException e) {e.printStackTrace();}
		game.startGame();
		long l = game.timeToEnd();
		// should be a Thread!!!
		while(game.isRunning()) {
			moveRobots(game, gg);
			if (l-game.timeToEnd()>120L) {
				kmlog.addRovotsAndFruits(gg,game);
				l=game.timeToEnd();
			}
		}
		String results = game.toString();

		System.out.println("Game Over: "+results);
		boolean b = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
				"save as kml?");
		if(b==true){
			String filename = JOptionPane.showInputDialog(f, "Enter name to file save game ");
			kmlog.save(filename);

		}


	}





	/**
	 * Moves each of the robots along the edge,
	 * in case the robot is on a node the next destination (next edge) is chosen (randomly).
	 * @param game
	 * @param gg
	 * @param
	 */
	private static void moveRobots(game_service game, graph gg) {
		List<String> log = game.move();
		if(log!=null) {
			long t = game.timeToEnd();
			for(int i=0;i<log.size();i++) {
				String robot_json = log.get(i);
				try {
					JSONObject line = new JSONObject(robot_json);
					JSONObject ttt = line.getJSONObject("Robot");
					int rid = ttt.getInt("id");
					int src = ttt.getInt("src");
					int dest = ttt.getInt("dest");

					if(dest==-1) {
						dest = nextNode(gg, src,game);
						game.chooseNextEdge(rid, dest);
						System.out.println("Turn to node: "+dest+"  time to end:"+(t/1000));
						System.out.println(ttt);
					}
				}
				catch (JSONException e) {e.printStackTrace();}
			}
		}
	}
	/**
	 * a very simple random walk implementation!
	 * @param g
	 * @param src
	 * @return
	 */
	private static int nextNode(graph g, int src,game_service game) {
		if (auto) {
			Graph_Algo graph_algo = new Graph_Algo(g);
			allFruits myFruits = new allFruits((DGraph) g, game);
			Fruit f = myFruits.closeTo(src);
			System.out.println("f = " + f);
			if (f == null) return -1;
			System.out.println("f = " + f.getEdge().toString());

			if (f.getEdge().getDest() == src) return f.getEdge().getSrc();
			if (f.getEdge().getSrc() == src) return f.getEdge().getDest();
			List<node_data> temp = graph_algo.shortestPath(src, f.getEdge().getSrc());
			if (temp == null || temp.isEmpty() || temp.size() == 1) return -1;
			return temp.get(1).getKey();
		}
		else {
			return moverobot(src,g);
		}
	}
	public static int putrobots(DGraph g, game_service game, int rs){
		int src = -1;
		if (StdDraw.isMousePressed()) {
			double x = StdDraw.mouseX();
			double y = StdDraw.mouseY();
			src = findNode(x, y, g);
		}
		return src;

	}

	private static int findNode(double x, double y, graph g) {
		Point3D p = new Point3D(x,y);
		for (node_data n :g.getV()){
			if (n.getLocation().distance2D(p)<0.0003){
				return n.getKey();
			}
		}
		return -1;
	}
	private static int moverobot(int src, graph g) {
		int next = -1;
		if (StdDraw.isMousePressed()) {
			double x = StdDraw.mouseX();
			double y = StdDraw.mouseY();
			 next = findNode(x, y, g);
			 if (g.getEdge(src,next)==null)
			 	return -1;
		}
		return next;
	}


}
