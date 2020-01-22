package gameClient;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.node_data;

public class KML_Logger {
    private final DGraph graph;
    private final allFruits fruits;
    private final allRobots robots;
    private StringBuilder kmlOut = new StringBuilder();
    private StringBuilder kmlRobots = new StringBuilder();
    private StringBuilder kmlFruits = new StringBuilder();
    private long time = 0;
    private String projectPath = System.getProperty("user.dir");

    public KML_Logger(DGraph graph, allFruits fruits, allRobots robots) {
        this.graph = graph;
        this.fruits = fruits;
        this.robots = robots;
        init();
        writeGraph();
        initRobot();
        initFruit();
    }

    private void writeRobots() {
        for (Iterator<Robot> it = robots.getRobots(); it.hasNext(); ) {
            Robot r = it.next();
            writeRobot(r);
        }
    }

    private void writeFruits() {
        for (Iterator<Fruit> it = fruits.getArrayFruit(); it.hasNext(); ) {
            Fruit f = it.next();
            writeFruit(f);
        }
    }

    private void writeFruit(Fruit fruit) {
        kmlFruits.append("<Placemark>\r\n");

        kmlFruits.append("<TimeSpan>\r\n<begin>");
        kmlFruits.append(time);
        kmlFruits.append("</begin>\r\n<end>");
        kmlFruits.append(time+1);
        kmlFruits.append("</end>\r\n</TimeSpan>\r\n");

        if(fruit.getType() < 0)
            kmlFruits.append("<styleUrl>#banana</styleUrl>");
        else
            kmlFruits.append("<styleUrl>#apple</styleUrl>");

        kmlFruits.append("<Point><coordinates>");
        kmlFruits.append(fruit.getLocation());
        kmlFruits.append("</coordinates></Point>\r\n");
        kmlFruits.append("</Placemark>\r\n");

    }

    private void writeRobot(Robot robot) {
        kmlRobots.append("<Placemark>\r\n");
        kmlRobots.append("<TimeSpan>\r\n<begin>");
        kmlRobots.append(time);
        kmlRobots.append("</begin>\r\n<end>");
        kmlRobots.append(time+1);
        kmlRobots.append("</end>\r\n</TimeSpan>\r\n");
        kmlRobots.append("<styleUrl>#robot</styleUrl>");
        kmlRobots.append("<Point><coordinates>");
        kmlRobots.append(robot.getlocation());
        kmlRobots.append("</coordinates></Point>\r\n");
        kmlRobots.append("</Placemark>\r\n");
    }

    private void initRobot() {
        kmlRobots.append("<Folder><name>Robots</name>\r\n");
        kmlRobots.append("<description>The location of the robots during the game with timeStamp</description>\r\n");
    }

    private void initFruit() {
        kmlFruits.append("<Folder><name>Fruits</name>\r\n");
        kmlFruits.append("<description>The location of the Fruits during the game with timeStamp</description>\r\n");
    }

    private void init() {
        kmlOut.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
        kmlOut.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">\r\n");
        kmlOut.append("  <Document>\r\n");
        kmlOut.append("    <name>OOP3</name>\r\n");
        kmlOut.append("    <Style id=\"edgesYellow\">\r\n");
        kmlOut.append("      <LineStyle>\r\n");
        kmlOut.append("        <color>7f000000</color>\r\n");
        kmlOut.append("        <width>6</width>\r\n");
        kmlOut.append("      </LineStyle>\r\n");
        kmlOut.append("    </Style>");
        kmlOut.append("    <Style id=\"robot\">\r\n");
        kmlOut.append("      <IconStyle>\r\n");
        kmlOut.append("        <Icon>\r\n<href>");
        kmlOut.append(projectPath+"/utils/beagle.png");
        kmlOut.append("        </href>\r\n</Icon>\r\n");
        kmlOut.append("      </IconStyle>\r\n");
        kmlOut.append("    </Style>");
        kmlOut.append("    <Style id=\"apple\">\r\n");
        kmlOut.append("      <IconStyle>\r\n");
        kmlOut.append("        <Icon>\r\n<href>");
        kmlOut.append(projectPath+"/utils/apple.png");
        kmlOut.append("        </href>\r\n</Icon>\r\n");
        kmlOut.append("      </IconStyle>\r\n");
        kmlOut.append("    </Style>");
        kmlOut.append("    <Style id=\"banana\">\r\n");
        kmlOut.append("      <IconStyle>\r\n");
        kmlOut.append("        <Icon>\r\n<href>");
        kmlOut.append(projectPath+"/utils/banana.png");
        kmlOut.append("        </href>\r\n</Icon>\r\n");
        kmlOut.append("      </IconStyle>\r\n");
        kmlOut.append("    </Style>");
    }

    private void writeGraph() {
        kmlOut.append("	<Folder><name>Graph</name><open>1</open>\r\n");
        kmlOut.append(" <description>game graph</description>\r\n");
        writEdges();
        writeNodes();
        kmlOut.append("</Folder>\r\n");
    }

    private void writeNodes() {
        kmlOut.append("		<Folder><name>nodes</name>\r\n");
        kmlOut.append("      <description>graph nodes</description>\r\n");
        for (node_data node : graph.getV()) {
            writeNode(node);
        }
        kmlOut.append("	</Folder>\r\n");
    }

    private void writeNode(node_data n) {
        kmlOut.append("<Placemark> \r\n");
        kmlOut.append("      <name>");
        kmlOut.append(n.getKey());
        kmlOut.append("</name>\r\n");
        kmlOut.append(" <Polygon> <outerBoundaryIs>  <LinearRing>  \r\n");
        kmlOut.append("  <coordinates>\r\n");

        double x = n.getLocation().x();
        double y = n.getLocation().y();
        double w = 0.0002;

        writeCoord(x-w, y-w);
        writeCoord(x-w, y+w);
        writeCoord(x+w, y+w);
        writeCoord(x+w, y-w);
        writeCoord(x-w, y-w);

        kmlOut.append("  </coordinates>\r\n");
        kmlOut.append(" </LinearRing> </outerBoundaryIs> </Polygon>\r\n");
        kmlOut.append(" <Style> \r\n");
        kmlOut.append("  <PolyStyle>  \r\n");
        kmlOut.append("   <color>#ff000000</color>\r\n");
        kmlOut.append("   <outline>1</outline>\r\n");
        kmlOut.append("  </PolyStyle> \r\n");
        kmlOut.append(" </Style>\r\n");
        kmlOut.append("</Placemark>\r\n");
    }

    private void writeCoord(double x, double y){
        kmlOut.append(x);
        kmlOut.append(",");
        kmlOut.append(y);
        kmlOut.append("\r\n");
    }

    private void writEdges() {
        kmlOut.append("		<Folder><name>edges</name>\r\n");
        kmlOut.append("      <description>graph edges</description>\r\n");

        for (node_data node : graph.getV()) {
            for (edge_data edge : graph.getE(node.getKey())) {
                writEdge(edge);
            }
        }

        kmlOut.append("	</Folder>\r\n");
    }

    private void writEdge(edge_data edge) {
        kmlOut.append("    <Placemark>\r\n");
        kmlOut.append("      <name>");
        kmlOut.append(edge);
        kmlOut.append("</name>\r\n");
        kmlOut.append("      <styleUrl>edges</styleUrl>\r\n");
        kmlOut.append("      <LineString>\r\n");
        kmlOut.append("        <coordinates>\r\n");
        kmlOut.append(graph.getNode(edge.getSrc()).getLocation());
        kmlOut.append("\r\n");
        kmlOut.append(graph.getNode(edge.getDest()).getLocation());
        kmlOut.append("\r\n");
        kmlOut.append("        </coordinates>\r\n");
        kmlOut.append("      </LineString>\r\n");
        kmlOut.append("    </Placemark>\r\n");
    }


    public void closeKml() {
        kmlRobots.append("</Folder>\r\n");
        kmlFruits.append("</Folder>\r\n");
        kmlOut.append(kmlRobots);
        kmlOut.append(kmlFruits);
        kmlOut.append("  </Document>\r\n");
        kmlOut.append("</kml>");
    }

    private String log() {
        return kmlOut.toString();
    }

    public void save(String file_name) {
        try {
            PrintWriter out = new PrintWriter(file_name);
            out.print(log());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void writeStatus() {
        writeRobots();
        writeFruits();
        time++;
    }
}
