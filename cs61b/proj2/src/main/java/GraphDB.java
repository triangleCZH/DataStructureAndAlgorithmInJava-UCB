import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Wraps the parsing functionality of the MapDBHandler as an example.
 * You may choose to add to the functionality of this class if you wish.
 * @author Alan Yao
 */
public class GraphDB {
    /**
     * Example constructor shows how to create and start an XML parser.
     * @param dbPath Path to the XML file to be parsed.
     */





    private Trie trie = new Trie();

    private HashMap<Long, LinkedList<GraphNode>> connectedNode = new HashMap<Long, LinkedList<GraphNode>>();
    private HashMap<Long, GraphNode> nodeinfo = new HashMap<Long, GraphNode>();


    public GraphDB(String dbPath) {
        try {
            File inputFile = new File(dbPath);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            MapDBHandler maphandler = new MapDBHandler(this);
            saxParser.parse(inputFile, maphandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        clean();
    }



    public void findAndDrawRoute(Long x_lat, Long x_lon, Long y_lat, Long y_lon){

    }

    /**
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    /**
     *  Remove nodes with no connections from the graph.
     *  While this does not guarantee that any two nodes in the remaining graph are connected,
     *  we can reasonably assume this since typically roads are connected.
     */
    private void clean() {
    }

//    public List<String> getLocationsByPrefix(String prefix) {
//        List<String> nameArray = new ArrayList<>();
//        ArrayList<Long> result = trie.lookupDefinition(prefix);
//        for (long a: result) {
//            GraphNode node = firstIteration.get(a);
//            String name = node.getName();
//            nameArray.add(name);
//        }
//        return nameArray;
//    }
//
//    public ArrayList<GraphNode> getLocations(String prefix) {
//        ArrayList<GraphNode> array = new ArrayList<>();
//        ArrayList<Long> result = trie.lookupDefinition(prefix);
//        for (long a: result) {
//            GraphNode node = firstIteration.get(a);
//            array.add(node);
//        }
//        return array;
//    }

    public void setConnectedNode(Long id,LinkedList list) {
        connectedNode.put(id, list);
    }

    public void setNodeinfo(Long id,GraphNode node) {
        nodeinfo.put(id, node);
    }


    public HashMap<Long, GraphNode> getNodeinfo() {
        return nodeinfo;
    }

//    public void addConnection(long id1, long id2) {
//        Connection tempConnect = new Connection(firstIteration.get(id1), firstIteration.get(id2));
//        GraphNode tempNode = firstIteration.get(id2);
//
//        if (connectPoint.containsKey(id1)) {
//            connectPoint.get(id1).add(tempNode);
//        } else {
//            LinkedList<GraphNode> tempList = new LinkedList<>();
//            tempList.add(tempNode);
//            connectedNode.put(id1, tempList);
//        }
//    }

    public double getDistance(GraphNode a, GraphNode b) {
        return Math.pow(Math.pow(Math.abs(a.getlat() - b.getlat()), 2)
                + Math.pow(Math.abs(a.getlon() - b.getlon()), 2), 0.5);
    }

    public double getDistance(GraphNode a, Point b) {
        return Math.pow(Math.pow(Math.abs(a.getlat() - b.getX()), 2)
                + Math.pow(Math.abs(a.getlon() - b.getY()), 2), 0.5);
    }

    public HashMap<Long, LinkedList<GraphNode>> getConnectedNode() {
        return connectedNode;
    }

    public Trie getTrie() {
        return trie;
    }

}
