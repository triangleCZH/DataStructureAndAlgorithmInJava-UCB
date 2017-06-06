/**
 * Created by cs61bl-cc on 8/3/16.
 */
public class Connection {
    private GraphNode from;
    private GraphNode to;
    private Point dep;
    private Point dest;

    public Connection(Point from, Point to) {
        this.dep = from;
        this.dest = to;
    }

    public Connection(GraphNode from, GraphNode to) {
        this.from = from;
        this.to = to;
    }


}
