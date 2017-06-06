
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.*;
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
    private HashMap<Long, GraphNode> nodeneighbour = new HashMap<Long, GraphNode>();
    private ArrayList<Long> indexList = new ArrayList<>();


    public GraphDB(String dbPath) {
        try {
            File inputFile = new File(dbPath);
            //System.out.println(dbPath);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            MapDBHandler maphandler = new MapDBHandler(this);
            saxParser.parse(inputFile, maphandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        clean();
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



    public List<String> getLocationsByPrefix(String prefix) {
        List<String> name = new ArrayList<String>();
        ArrayList<Long> idSet = trie.search(prefix);
        for(long a: idSet) {
            GraphNode node = nodeinfo.get(a);

            name.add(node.getname());
        }
        return name;
    }

   public ArrayList<GraphNode> getLocations(String prefix) {
        ArrayList<GraphNode> name = new ArrayList<>();
       ArrayList<Long> idSet = trie.search(prefix);
        for (long a: idSet) {
            GraphNode node = nodeinfo.get(a);
            name.add(node);
        }
        return name;
    }

    private void complete(Trie trietemp) {
        Set set = nodeinfo.keySet();
        Iterator<Long> iterator = set.iterator();
        while (iterator.hasNext()) {
            long c = iterator.next();
            GraphNode child = nodeinfo.get(c);
            String name = child.getname();
            long id = child.getid();
            if (name != null) {
                name = cleanString(name);
                //System.out.println(name);
                trietemp.insert(name, id);
            }
        }
    }

    public void setConnectedNode(Long id,LinkedList list) {
        connectedNode.put(id, list);
    }

    public void setNodeinfo(Long id,GraphNode node) {
        nodeinfo.put(id, node);
    }


    public HashMap<Long, GraphNode> getNodeinfo() {
        return nodeinfo;
    }

    public void addConnection(long id1, long id2) {

        GraphNode thatNode = nodeinfo.get(id2);
        GraphNode thisNode = nodeinfo.get(id1);
        Connection newConnection = new Connection(thisNode, thatNode);
         if(connectedNode.containsKey(id1)){
             connectedNode.get(id1).add(thatNode);
         }
        else{
             LinkedList<GraphNode> newList = new LinkedList<GraphNode>();
             newList.add(thatNode);
             connectedNode.put(id1, newList);
         }
    }

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

    public HashMap<Long, GraphNode> getNodeneighbour() {
        return nodeneighbour;
    }

    public void setNodeneighbour(HashMap<Long, GraphNode> nodeneighbour) {
        this.nodeneighbour = nodeneighbour;
    }

    //return a list of the shortest path using A* from start to end
    public ArrayList<GraphNode> findPath(GraphNode start, GraphNode end) {

    /*initialization*/
        HashSet<GraphNode> close = new HashSet<>();
        //visited
        HashMap<GraphNode, Double> distance = new HashMap<>();
        //for the visited nodes, their distance from the starting node
        HashMap<GraphNode, GraphNode> path = new HashMap<>();
        //key is current node and value is next node, used to trace back
        PriorityQueue<GraphNode> open = new PriorityQueue<>((o1, o2)->
                ((distance.get(o2) + getDistance(o2, end))
                        > (distance.get(o1) + getDistance(o1, end))) ? -1 : 1);
        //lambda that put the nodes next to visit according to priority
        LinkedList<GraphNode> neighbors = new LinkedList<>();
        //what neighbors this node has
        ArrayList<GraphNode> ret = new ArrayList<>();

    /*starting case*/
        GraphNode currNode;
        GraphNode prevNode;
        open.add(start);
        distance.put(start, 0.0);

    /*looping*/
        while (!close.contains(end)) {
            // if close contains end, end is polled and explored, then pathFinding finished
            currNode = open.poll();
            double abc = currNode.getid();
            close.add(currNode);
            neighbors = connectedNode.get(currNode.getid());

            for (GraphNode o : neighbors) {
                double newDistance =  distance.get(currNode) + getDistance(currNode, o);
                if (!distance.containsKey(o)) {
                    distance.put(o, newDistance);
                    path.put(o, currNode); // if (currNode, o) then for the map path, the o is always updating
                } else if (newDistance < distance.get(o)) {
                    distance.put(o, newDistance);
                    path.put(o, currNode);
                }
                if(!open.contains(o) && !close.contains(o)) { //unvisited and unexplored
                    open.add(o);
                }
            }
        }

    /*trace back to the path*/
        prevNode = end;
        while (prevNode != start) {
            ret.add(prevNode);
            prevNode = path.get(prevNode);
        }
        ret.add(end);
        return ret;
    }

    public void addIndex(long index) {
        if (!indexList.contains(index)) {
            indexList.add(index);
        }
    }

    public void addNeighbour(long id) {
        nodeneighbour.put(id, nodeneighbour.get(id));
    }


    public GraphNode nearestNode(Point destination) {
        //Set<Long> set = connectedNode.keySet();
        double distance = 2147483647;
        long id = 0L;
        for (long a : indexList) { // FIXME: 8/7/16  need a container that holds all the nodes and can iterate
            GraphNode curr = nodeneighbour.get(a);
            if (getDistance(curr, destination) < distance) {
                id = a;
                distance = getDistance(curr, destination);
            }
        }
        return nodeneighbour.get(id);
    }
}
