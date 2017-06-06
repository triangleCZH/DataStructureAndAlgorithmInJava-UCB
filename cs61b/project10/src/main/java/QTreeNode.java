/**
 * Created by USER on 2016/7/21.
 */
public class QTreeNode {
    /**
     * a treenode is a tile,
     * root is the largest tile,
     * and the deepest is the leaf treenode*/
    public String name;

    /**
     * use the upper left and lower right point to specify a tile*/
    Point ul;
    Point lr;
    public Point center;
    /**
     * width distance per pixel
     * and height distance per pixel*/
    public double width;
    public double height;

    /**
     * record which depth this node is in*/
    public int depth;

    /**
     * each node has four children, one two three four*/
    public QTreeNode a;
    public QTreeNode b;
    public QTreeNode c;
    public QTreeNode d;

    public QTreeNode(String name, Point ul, Point lr, int depth) {
        this.name = name;
        this.ul = ul;
        this.lr = lr;
        this.depth = depth;
        this.center = new Point((ul.x + lr.x) / 2, (ul.y + lr.y) / 2);
    }

    public void buildTree(int depth) { //originally depth is 0 from root
        if (depth == 7) {
            return;
        }

        double x0 = this.ul.x;
        double x1 = this.center.x;
        double x2 = this.lr.x;
        double y0 = this.ul.y;
        double y1 = this.center.y;
        double y2 = this.lr.y;

        depth++;
        this.a = new QTreeNode(this.name + String.valueOf('a' - 96), new Point(x0, y0), new Point(x1, y1), depth);
        this.b = new QTreeNode(this.name + String.valueOf('b' - 96), new Point(x1, y0), new Point(x2, y1), depth);
        this.c = new QTreeNode(this.name + String.valueOf('c' - 96), new Point(x0, y1), new Point(x1, y2), depth);
        this.d = new QTreeNode(this.name + String.valueOf('d' - 96), new Point(x1, y1), new Point(x2, y2), depth);
        a.buildTree(depth);
        b.buildTree(depth);
        c.buildTree(depth);
        d.buildTree(depth);
    }


}
