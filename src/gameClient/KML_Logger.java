package gameClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import Server.game_service;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.node_data;

public class KML_Logger {

    private StringBuilder kmlall = new StringBuilder();

    public KML_Logger() {
        kmlall.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<kml xmlns=\"http://earth.google.com/kml/2.2\">\n" +
                "  <Document>\n" +
                "    <name>Points with TimeStamps</name>\n" +
                "    <Style id=\"paddle-a\">\n" +
                "      <IconStyle>\n" +
                "        <Icon>\n" +
                "          <href>http://maps.google.com/mapfiles/kml/paddle/A.png</href>\n" +
                "        </Icon>\n" +
                "        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\n" +
                "      </IconStyle>\n" +
                "    </Style>\n" +
                "    <Style id=\"paddle-b\">\n" +
                "      <IconStyle>\n" +
                "        <Icon>\n" +
                "          <href>http://maps.google.com/mapfiles/kml/paddle/B.png</href>\n" +
                "        </Icon>\n" +
                "        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\n" +
                "      </IconStyle>\n" +
                "    </Style>\n" +
                "    <Style id=\"hiker-icon\">\n" +
                "      <IconStyle>\n" +
                "        <Icon>\n" +
                "          <href>http://maps.google.com/mapfiles/ms/icons/hiker.png</href>\n" +
                "        </Icon>\n" +
                "        <hotSpot x=\"0\" y=\".5\" xunits=\"fraction\" yunits=\"fraction\"/>\n" +
                "      </IconStyle>\n" +
                "    </Style>\n" +
                "    <Style id=\"check-hide-children\">\n" +
                "      <ListStyle>\n" +
                "        <listItemType>checkHideChildren</listItemType>\n" +
                "      </ListStyle>\n" +
                "    </Style>\n" +
                " ");

    }

    public void addGraph(DGraph g) {
        for (node_data n : g.getV()) {
            kmlall.append("<Placemark>\n" + "    <description>" + "place num:");
            kmlall.append(n.getKey());
            kmlall.append("</description>\n");
            kmlall.append("    <Point>\n");
            kmlall.append("      <coordinates>");
            kmlall.append(n.getLocation().x());
            kmlall.append(",");
            kmlall.append(n.getLocation().y());
            kmlall.append(",0</coordinates>\n");
            kmlall.append("    </Point>\n");
            kmlall.append("  </Placemark>\n");
        }

    }

    public void addRovotsAndFruits(DGraph g, game_service game) {
        allRobots robots = new allRobots(g, game);
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        String timeStr = df.format(date);
        String timeStr2 = df2.format(date);
        String finalDate = timeStr + "T" + timeStr2 + "Z";
        allFruits fruits = new allFruits(g, game);
        Iterator<Robot> ItRobot = robots.getRobots();
        Iterator<Fruit> ItFruit = fruits.getArrayFruit();
        while (ItRobot.hasNext()) {
            Robot temp = ItRobot.next();
            kmlall.append("<Placemark>\n" + "      <TimeStamp>\n" + "        <when>");
            kmlall.append(finalDate).append("</when>\n");
            kmlall.append("      </TimeStamp>\n");
            kmlall.append("      <styleUrl>#hiker-icon</styleUrl>\n");
            kmlall.append("      <Point>\n").append("        <coordinates>");
            kmlall.append(temp.getlocation().x());
            kmlall.append(",");
            kmlall.append(temp.getlocation().y());
            kmlall.append(",0</coordinates>\n");
            kmlall.append("      </Point>\n");
            kmlall.append("    </Placemark>");
        }
        while (ItFruit.hasNext()) {
            Fruit temp = ItFruit.next();
            String typer = "#paddle-a";
            if (temp.getType() == 1) {
                typer = "#paddle-b";
            }
            kmlall.append("<Placemark>\n" + "      <TimeStamp>\n" + "        <when>");
            kmlall.append(finalDate);
            kmlall.append("</when>\n");
            kmlall.append("      </TimeStamp>\n");
            kmlall.append("      <styleUrl>");
            kmlall.append(typer).append("</styleUrl>\n");
            kmlall.append("      <Point>\n");
            kmlall.append("        <coordinates>");
            kmlall.append(temp.getLocation().x());
            kmlall.append(",");
            kmlall.append(temp.getLocation().y());
            kmlall.append(",0</coordinates>\n");
            kmlall.append("      </Point>\n");
            kmlall.append("    </Placemark>");

        }


    }


    public void save(String name) throws Exception {
        kmlall.append("  </Document>\n" +
                "</kml>");
        File file = new File(name + ".kml");
        FileWriter writer = new FileWriter(file);
        writer.write(String.valueOf(kmlall));
        writer.close();

    }

    public StringBuilder getLgerOfGame() {
        return kmlall;
    }
}



