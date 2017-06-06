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
            adjLists[k] = new LinkedList<Edge>(); //each node's list of its neighbours
        }
        vertexCount = numVertices;
    }

    // Change the vertex the iterator will start DFS from
    public void setStartVertex(int v){
        if (v < vertexCount && v >= 0){ //avoid exception
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
        if (v1 < vertexCount && v1 >=0 && v2 < vertexCount && v2 >=0) {
            adjLists[v1].add(new Edge(v1, v2, edgeInfo));
        }
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) { // undirected should use directed method!
        //your code here
        addEdge(v1, v2, edgeInfo);
        addEdge(v2, v1, edgeInfo);
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
        if (from < vertexCount && from >=0 && to < vertexCount && to >=0) {
            LinkedList<Edge> temp = adjLists[from];
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).to == to) {
                    return true;
                }
            }
        }
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
        /*List<Edge> itsNei = new ArrayList<>();
        LinkedList<Edge> temp = adjLists[vertex - 1];
        for (Edge a : adjLists[vertex - 1]) {
            itsNei.add()
        }*/
        LinkedList<Integer> b = new LinkedList<>();
        if (vertex < vertexCount && vertex >=0) {
            for (Edge a : adjLists[vertex]) {
                b.add(a.to);
            }
        }
        return b;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        if (vertex < vertexCount && vertex >=0) {
            for (int i = 0; i < vertexCount; i++) {
                for (Edge a : adjLists[i]) {
                    if (a.to == vertex) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public Iterator<Integer> iterator(){
        return new TopologicalIterator();
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
        }

        public boolean hasNext() {
            //your code here
            return adjLists.length != visited.size();
        }

        public Integer next() {
            //your code here
            /**/
            int temp = fringe.pop();
            visited.add(temp);
            for(Object o : neighbors(temp)) {
                if (!visited.contains(o)) {
                    fringe.push((int)o);
                }
            }
            if (fringe.isEmpty()) {
                for (int i = 0; i < adjLists.length; i++) {
                    if (!visited.contains(i)) {
                        fringe.add(i);
                        break;
                    }
                }
            }

            return temp;


        }

        //ignore this method
        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

    }


    private class DFSIterator1 implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private HashSet<Integer> visited;

        public DFSIterator1(Integer start) {
            //your code here
            fringe = new Stack<>();
            visited = new HashSet<>();
            fringe.push(start);
        }

        public boolean hasNext() {
            //your code here
            return !fringe.isEmpty();
        }

        public Integer next() {
            //your code hereint temp = fringe.pop();
            int temp = fringe.pop();
            visited.add(temp);
            for(Object o : neighbors(temp)) {
                if (!visited.contains(o)) {
                    fringe.push((int)o);
                }
            }
            return temp;

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

    public ArrayList<Integer> visitPath(int startVertex) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator1(startVertex);

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
        if (startVertex == stopVertex) {
            return true;
        }
        return visitPath(startVertex).contains(stopVertex);
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        ArrayList<Integer> a = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();
        pathHelp(a, startVertex, stopVertex, visited);
        return a;

        // you supply the body of this method
    }

    public void pathHelp(ArrayList<Integer> a, int startVertex, int stopVertex, HashSet<Integer> visited) {
        if (pathExists(startVertex, stopVertex)) {
            a.add(startVertex);
            visited.add(startVertex);
            for (Object o : neighbors(startVertex)) {
                if (pathExists( (int)o, stopVertex) && !visited.contains(o)) {
                    pathHelp(a, (int)o, stopVertex, visited);
                    return;
                }
            }
        }
    }

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
        private int[] currentInDegree;
        private HashSet<Integer> visited;

        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            currentInDegree = new int[vertexCount];
            for (int i = 0; i < vertexCount; i++) {
                currentInDegree[i] = inDegree(i);
                if (inDegree(i) == 0) {
                    fringe.push(i);
                }
            }
            visited = new HashSet<>();
            // more statements go here
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            int store = fringe.pop();
            visited.add(store);
            for (Object o : neighbors(store)) {
                if (!visited.contains(o)) {
                    currentInDegree[(int)o]--;
                    if (currentInDegree[(int)o] == 0) {
                        fringe.add((int)o);
                    }
                }
            }
            return store;
            // you supply the real body of this method
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

    }

    private class Edge {

        private Integer from; // FROM WHERE TO WHERE
        private Integer to;
        private Object edgeInfo; // what is this node

        public Edge(int from, int to, Object info) {
            this.from = new Integer(from);
            this.to = new Integer(to);
            this.edgeInfo = info;
        }

        public Integer to() {
            return to;
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

        Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(0, 4);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(2, 3);
        g1.addEdge(4, 3);
        System.out.println(g1.visitPath(1).toString());
        System.out.println("Traversal starting at 0");
        result = g1.visitAll(0);
        Iterator<Integer> iter;
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }

        result = g1.visitAll(1);
        Iterator<Integer> iter3;
        iter3 = result.iterator();
        while (iter3.hasNext()) {
            System.out.println(iter3.next() + " lala");
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
        //System.out.println(g1.path(0, 4).toString());
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
        System.out.println("Path from 4 to 0");
        result = g1.path(4, 0);
        if (result.size() != 0) {
            System.out.println("*** should be no path!");
        }

        Graph g2 = new Graph(5);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(0, 4);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        g2.addEdge(4, 3);
        System.out.println();
        System.out.println();
        System.out.println("Topological sort");
        result = g2.topologicalSort();
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        Graph g10 = new Graph(5);
        g10.addEdge(0, 1);
        g10.addEdge(0, 2);
        g10.addEdge(0, 4);
        g10.addEdge(1, 2);
        g10.addEdge(2, 0);
        g10.addEdge(2, 3);
        g10.addUndirectedEdge(4, 3);
        System.out.println("Traversal starting at 0");
        result = g10.visitAll(0);
        Iterator<Integer> iter1;
        iter1 = result.iterator();
        while (iter1.hasNext()) {
            System.out.println(iter1.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println(g10.path(0, 4).toString());
    }

}
