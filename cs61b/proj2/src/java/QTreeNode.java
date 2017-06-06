/**
 * Created by USER on 2016/7/21.
 */
public class QTreeNode {
    /**
     * a treenode is a tile,
     * root is the largest tile,
     * and the deepest is the leaf treenode*/
    private String name;

    /**
     * use the upper left and lower right point to specify a tile*/
    Point ul;
    Point lr;
    private Point center;
    /**
     * width distance per pixel
     * and height distance per pixel*/
    private double width;
    private double height;

    /**
     * record which depth this node is in*/
    private int depth;

    /**
     * each node has four children, one two three four*/
    private QTreeNode a;
    private QTreeNode b;
    private QTreeNode c;
    private QTreeNode d;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public QTreeNode getA() {
        return a;
    }

    public void setA(QTreeNode a) {
        this.a = a;
    }

    public QTreeNode getB() {
        return b;
    }

    public void setB(QTreeNode b) {
        this.b = b;
    }

    public QTreeNode getC() {
        return c;
    }

    public void setC(QTreeNode c) {
        this.c = c;
    }

    public QTreeNode getD() {
        return d;
    }

    public void setD(QTreeNode d) {
        this.d = d;
    }

    public QTreeNode(String name, Point ul, Point lr, int depth) {
        this.name = name;
        this.ul = ul;
        this.lr = lr;
        this.depth = depth;
        this.center = new Point((ul.getX() + lr.getX()) / 2, (ul.getY() + lr.getY()) / 2);
    }

    public void buildTree(int depth1) { //originally depth is 0 from root
        if (depth1 == 7) {
            return;
        }

        double x0 = this.ul.getX();
        double x1 = this.center.getX();
        double x2 = this.lr.getX();
        double y0 = this.ul.getY();
        double y1 = this.center.getY();
        double y2 = this.lr.getY();

        depth1++;
        this.a = new QTreeNode(this.name + String.valueOf('a' - 96),
                new Point(x0, y0), new Point(x1, y1), depth1);
        this.b = new QTreeNode(this.name + String.valueOf('b' - 96),
                new Point(x1, y0), new Point(x2, y1), depth1);
        this.c = new QTreeNode(this.name + String.valueOf('c' - 96),
                new Point(x0, y1), new Point(x1, y2), depth1);
        this.d = new QTreeNode(this.name + String.valueOf('d' - 96),
                new Point(x1, y1), new Point(x2, y2), depth1);
        a.buildTree(depth1);
        b.buildTree(depth1);
        c.buildTree(depth1);
        d.buildTree(depth1);
    }


}
