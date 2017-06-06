import java.util.ArrayList;

/**
 * Created by USER on 2016/7/21.
 */
public class QuadTree {

    /*this arraylist contains qtreenodes that need to be rastered*/
    public ArrayList<QTreeNode> rasteredImage = new ArrayList<QTreeNode>();

    public QTreeNode root;

    public Point topleft = new Point(-122, 37);
    public Point bottomright = new Point(-123, 38);

    /**
     * record the width and height of the pictures for every-depth tiles*/
    //public static double[] widthMap = new double[8];
    //public static double[] heightMap = new double[8];

    public QuadTree() {
        double a1 = MapServer.ROOT_ULLON;
        double a2 = MapServer.ROOT_ULLAT;
        double b1 = MapServer.ROOT_LRLON;
        double b2 = MapServer.ROOT_LRLAT;
        this.root = new QTreeNode("", new Point(a1,a2), new Point(b1, b2),  0);
        /*heightMap[0] = a2 - b2;
        widthMap[0] = b1 - a1;
        for (int i = 1;i < 8;i++) {
            heightMap[i] = heightMap[i - 1] / 2;
            widthMap[i] = widthMap[i - 1] / 2;
        }*/
    }
    //the qtreenode constructor need arguments: name, p1, p2, height, width, depth;


    public QTreeNode getRoot() {
        return this.root;
    }

    public void buildTree() {
        root.buildTree(0);
    }

    public void searchTree(int depthWanted, QTreeNode root) {
        searchNode(depthWanted, 0, root);
    }


    /*recurse to search through the whole tree*/
    public void searchNode(int depthWanted, int depth, QTreeNode root) {
        if (depthWanted == depth) {
            if (contain(root)) {
                rasteredImage.add(root);
                if (root.ul.x < topleft.x) {
                    topleft.x = root.ul.x;
                }
                if (root.lr.x > bottomright.x) {
                    bottomright.x = root.lr.x;
                }
                if (root.ul.y > topleft.y) {
                    topleft.y = root.ul.y;
                }
                if (root.lr.y < bottomright.y) {
                    bottomright.y = root.lr.y;
                }
            }
            return;
        }
        if (contain(root.a)) {
            searchNode(depth,depth + 1, root.a);
        }
        if (contain(root.b)) {
            searchNode(depth,depth + 1, root.b);
        }
        if (contain(root.c)) {
            searchNode(depth,depth + 1, root.c);
        }
        if (contain(root.d)) {
            searchNode(depth,depth + 1, root.d);
        }
    }


    /*judge if the two triangles intersect each other*/
    public boolean contain(QTreeNode a) {
        double xa1 = a.lr.x;
        double xa2 = a.ul.x;
        double xb1 = MapServer.lrlon;
        double xb2 = MapServer.ullon;
        double ya2 = a.ul.y;
        double ya1 = a.lr.y;
        double yb2 = MapServer.ullat;
        double yb1 = MapServer.lrlat;
        return (((xb1 >= xa1) && (xb1 <= xa2)) || ((xb2 >= xa1) && (xb2 <= xa2))) &&
                (((yb1 >= ya1) && (yb1 <= ya2)) || ((yb2 >= ya1) && (yb2 <= ya2)));
    }







}
