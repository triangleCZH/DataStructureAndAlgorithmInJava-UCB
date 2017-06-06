import java.util.*;
public class Graph implements Iterable<Integer>{
    private LinkedList<Edge>[] adjLists;
    private int vertexCount;
    private int startVertex;
    // Initialize a graph with the given number of vertices and no edges.
    public Graph(int numVertices) {
        adjLists = new LinkedList[numVertices];
        startVertex = 0;
        for (int k = 0; k < numVertices; k++) {
            adjLists[k] = new LinkedList<Edge>();
        }
        vertexCount = numVertices;
    }
    // Change the vertex the iterator will start DFS from
    public void setStartVertex(int v){
        if (v < vertexCount && v >= 0){
            startVertex = v;
        } else {
            throw new IllegalArgumentException("Cannot set iteration start vertex to " + v + ".");
        }
    }
    // Add to the graph a directed edge from vertex v1 to vertex v2.
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, null);
    }
    // Add to the graph an undirected edge from vertex v1 to vertex v2.
    public void addUndirectedEdge(int v1, int v2) {
        addUndirectedEdge(v1, v2, null);
    }
    // Add to the graph a directed edge from vertex v1 to vertex v2,
// with the given edge information.
    public void addEdge(int v1, int v2, Object edgeInfo) {
//your code here
        Edge edge = new Edge(v1, v2, edgeInfo);
        if(adjLists[v1].contains(edge)){
            return;
        }
        adjLists[v1].addLast(edge);
    }
    // Add to the graph an undirected edge from vertex v1 to vertex v2,
// with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
//your code here
        Edge edge1 = new Edge(v1, v2, edgeInfo);
        Edge edge2 = new Edge(v2, v1, edgeInfo);
        if(!adjLists[v1].contains(edge1)){
            adjLists[v1].addLast(edge1);
        }
        if(!adjLists[v2].contains(edge2)){
            adjLists[v2].addLast(edge2);
        }
    }
    // Return true if there is an edge from vertex "from" to vertex "to";
// return false otherwise.
    public boolean isAdjacent(int from, int to) {
//your code here
// LinkedList<Edge> list = adjLists[from];
// for (Edge a : list) {
// if(a.from()==from&&a.to()==to){
// return true;
// }
// };
// return false;
        return pathExists(from, to);
    }
    // Returns a list of all the neighboring vertices 'u'
// such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
// your code here
        LinkedList<Integer> result = new LinkedList<>();
        LinkedList<Edge> list = adjLists[vertex];
        for (Edge a : list) {
            result.add(a.to());
        }
//List<Integer> list = new List<Integer>();
        return result;
    }
    // Return the number of incoming vertices for the given vertex,
// i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
//your code here
        for(int i=0; i<adjLists.length; i++){
            if(i==vertex){ continue;}
            if(isAdjacent(i,vertex)){
                count++;
                continue;
            }
        }
        return count;
    }
    public Iterator<Integer> iterator(){
        return new TopologicalIterator();
    }


    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
        //your code here...
        PriorityQueue<Integer> fringe = new PriorityQueue<Integer>();
        HashMap<Integer, Integer> previous = new HashMap<Integer, Integer>();
        HashMap<Integer, Double> dist = new HashMap<Integer, Double>();
        HashSet<Integer> visited = new HashSet<Integer>();
            dist.put(startVertex, 0.0);
            fringe.add(startVertex);
            while (!fringe.isEmpty()) {
                int a = fringe.poll();
                if (visited.contains(a)) {  //fixme now the node part is so strange, and maybe int is not the correct choice
                    continue;
                }
                if (a == startVertex) {
                    return null; //fixme for draft
                }
                for (Object b : neighbors(a)) {
                    double newDst = (double)adjLists[a].get((int)b).info() + dist.get(a);
                    if (!visited.contains(b) && newDst < dist.get(b)) {
                        dist.replace((int)b, newDst);
                        fringe.add((int)b);
                        previous.put((int)b, startVertex);
                    }
                }
            }
    }
    // A class that iterates through the vertices of this graph, starting with a given vertex.
// Does not necessarily iterate through all vertices in the graph: if the iteration starts
// at a vertex v, and there is no path from v to a vertex w, then the iteration will not
// include w
    private class DFSIterator implements Iterator<Integer> {
        private Stack<Integer> fringe;
        private HashSet<Integer> visited;
        public DFSIterator(Integer start) {
//your code here
            fringe = new Stack<>();
            visited = new HashSet<>();
            fringe.push(start);
            visited.add(start);
        }
        public boolean hasNext() {
//your code here
            return !fringe.isEmpty();
        }
        public Integer next() {
//your code here
            if(!hasNext()){
                return null;
            }
            int next = fringe.pop();
//list is the list of neighbors for vertex
            List list = neighbors(next);
            for (Object a: list) {
                if(visited.contains(a)){
                    continue;
                }
                fringe.push((int)a);
                visited.add((int)a);
            }
            return next;
        }
        //ignore this method
        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }
    }
    // Return the collected result of iterating through this graph's
// vertices as an ArrayList.
    public ArrayList<Integer> visitAll(int startVertex) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }
    // Returns true iff there exists a path from STARVETEX to
// STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
// in this graph. If STARVERTEX == STOPVERTEX, returns true.
    public boolean pathExists(int startVertex, int stopVertex) {
// your code here
        ArrayList<Integer> fetch_result = visitAll(startVertex);
        if(fetch_result.contains(stopVertex)){
            return true;
        }
        return false;
    }
    // Returns the path from startVertex to stopVertex.
// If no path exists, returns an empty arrayList.
// If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
// you supply the body of this method
        int start = startVertex;
        int stop = stopVertex;
        int previous;
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> route = new HashMap<>();
        Stack<Integer> visited = new Stack<>();
        Stack<Integer> reverse = new Stack<>();
        int parent;
        if(!pathExists(startVertex,stopVertex)){
            return new ArrayList<Integer>();
        }
        else if(startVertex == stopVertex){
            list.add(startVertex);
            return list;
        }
        else{
            while(!neighbors(start).contains(stopVertex)){
                List neighbor = neighbors(start);
                for (Object a: neighbor) {
                    if(!pathExists((int)a,stopVertex)){
                        continue;
                    }
                    if(visited.contains(a)){
                        continue;
                    }
                    else {
                        route.put((int) a, start);
                        visited.push(start);
                        start = (int) a;
                        break;
                    }
                }
            }
            route.put(stopVertex,start);
            list.add(stopVertex);
            previous = route.get(stopVertex);
            list.add(previous);
            while(previous!=startVertex){
                int temp = route.get(previous);
                list.add(temp);
                previous = temp;
            }
            for(int i=0; i<list.size(); i++){
                reverse.push(list.get(i));
            }
            int i=0;
            while(!reverse.empty()){
                int temp = reverse.pop();
                list.set(i,temp);
                i++;
            }
//list.add(startVertex);
            return list;
        }
    }
    //public ArrayList<Integer> helpPath(ArrayList<Integer> list, int startVertex, int stopVertex){
// List neighbors = neighbors(startVertex);
// if(neighbors.contains(stopVertex)){
// list.add(stopVertex);
// return list;
// }
// else{
// for (Object a: neighbors) {
// if(list.contains(a)){
// continue;
// }
// if(pathExists((int)a, stopVertex)){
// list.add((int)a);
// list = helpPath(list,(int)a,stopVertex);
// }
// }
// }
// return list;
//}
    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new TopologicalIterator();
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }
    private class TopologicalIterator implements Iterator<Integer> {
        private Stack<Integer> fringe;
        // more instance variables go here
        int[] currentInDegree;
        public TopologicalIterator() {
            fringe = new Stack<Integer>();
// more statements go here
            currentInDegree = new int[adjLists.length];
            for(int i=0; i<adjLists.length;i++){
                currentInDegree[i]=inDegree(i);
            }
            for(int i=0; i<adjLists.length-1;i++){
                if(currentInDegree[i]==0){
                    fringe.push(i);
                }
            }
        }
        public boolean hasNext() {
            return !fringe.isEmpty();
        }
        public Integer next() {
// you supply the real body of this method
            int next = fringe.pop();
            List list = neighbors(next);
            for(Object a : list){
                currentInDegree[(int)a]--;
                if(currentInDegree[(int)a]==0){
                    fringe.push((int)a);
                }
            }
            return next;
        }
        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }
    }
    private class Edge {
        private Integer from;
        private Integer to;
        private Object edgeInfo;
        public Edge(int from, int to, Object info) {
            this.from = new Integer(from);
            this.to = new Integer(to);
            this.edgeInfo = info;
        }
        public Integer to() {
            return to;
        }
        public Integer from() {
            return from;
        }
        public Object info() {
            return edgeInfo;
        }
        public String toString() {
            return "(" + from + "," + to + ",dist=" + edgeInfo + ")";
        }
    }
    public static void main(String[] args) {
        ArrayList<Integer> result;
        Graph g1 = new Graph(8);
        g1.addUndirectedEdge(0,1);
        g1.addUndirectedEdge(0,2);
        g1.addUndirectedEdge(1,3);
        g1.addUndirectedEdge(1,4);
        g1.addUndirectedEdge(2,5);
        g1.addUndirectedEdge(2,6);
        g1.addUndirectedEdge(2,7);
// g1.addEdge(0, 2);
// g1.addEdge(0, 4);
// g1.addEdge(1, 2);
// g1.addEdge(2, 0);
// g1.addEdge(2, 3);
// g1.addEdge(4, 3);
        System.out.println("Traversal starting at 0");
        result = g1.visitAll(0);
        Iterator<Integer> iter;
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 2");
        result = g1.visitAll(2);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 3");
        result = g1.visitAll(3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 4");
        result = g1.visitAll(4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 3");
        result = g1.path(0, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 4");
        result = g1.path(0, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 3");
        result = g1.path(1, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 4");
        result = g1.path(1, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 6 to 3");
        result = g1.path(6, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
// if (result.size() != 0) {
// System.out.println("*** should be no path!");
// }
// Graph g2 = new Graph(5);
// g2.addEdge(0, 1);
// g2.addEdge(0, 2);
// g2.addEdge(0, 4);
// g2.addEdge(1, 2);
// g2.addEdge(2, 3);
// g2.addEdge(4, 3);
// System.out.println();
// System.out.println();
// System.out.println("Topological sort");
// result = g2.topologicalSort();
// iter = result.iterator();
// while (iter.hasNext()) {
// System.out.println(iter.next() + " ");
// }
    }
//
}