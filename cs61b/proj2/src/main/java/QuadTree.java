import java.util.ArrayList;

/**
 * Created by USER on 2016/7/21.
 */
public class QuadTree {

    /*this arraylist contains qtreenodes that need to be rastered*/
    private ArrayList<QTreeNode> R = new ArrayList<QTreeNode>();

    private QTreeNode root;

    /**
     * record the width and height of the pictures for every-depth tiles*/

    private static double[] widthMap = new double[8];
    private static double[] heightMap = new double[8];

    public QuadTree() {
        double a1 = MapServer.ROOT_ULLON;
        double a2 = MapServer.ROOT_ULLAT;
        double b1 = MapServer.ROOT_LRLON;
        double b2 = MapServer.ROOT_LRLAT;
        this.root = new QTreeNode("", new Point(a1, a2), new Point(b1, b2),  0);
        heightMap[0] = a2 - b2;
        widthMap[0] = b1 - a1;
        for (int i = 1; i < 8; i++) {
            heightMap[i] = heightMap[i - 1] / 2;
            widthMap[i] = widthMap[i - 1] / 2;
        }
    }
    //the qtreenode constructor need arguments: name, p1, p2, height, width, depth;


    public QTreeNode getRoot() {
        return this.root;
    }

    public ArrayList<QTreeNode> getR() {
        return R;
    }

    public void setR(ArrayList<QTreeNode> r) {
        this.R = r;
    }

    public void buildTree() {
        root.buildTree(0);
    }

    public void searchTree(int depthWanted, QTreeNode root1) {
        searchNode(depthWanted, 0, root1);
    }


    /*recurse to search through the whole tree*/
    public void searchNode(int depthWanted, int depth, QTreeNode root1) {
        if (contain(root1)) {
            if (depthWanted == depth) { //2 == 2
                R.add(root1);
                return;
            }
            if (contain(root1.getA())) {
                searchNode(depthWanted, depth + 1, root1.getA());
            }
            if (contain(root1.getB())) {
                searchNode(depthWanted, depth + 1, root1.getB());
            }
            if (contain(root1.getC())) {
                searchNode(depthWanted, depth + 1, root1.getC());
            }
            if (contain(root1.getD())) {
                searchNode(depthWanted, depth + 1, root1.getD());
            }
        }
    }


    /*judge if the two triangles intersect each other*/
    public boolean contain(QTreeNode a) {
        double xa1 = a.lr.getX();
        double xa2 = a.ul.getX();
        double xb1 = MapServer.lrlon;
        double xb2 = MapServer.ullon;
        double ya2 = a.ul.getY();
        double ya1 = a.lr.getY();
        double yb2 = MapServer.ullat;
        double yb1 = MapServer.lrlat;
        return (!(((xb1 + xa1 - xa2 - xb2) <= (xb1 - xa2))
                || ((xb1 + xa1 - xa2 - xb2) <= (xa1 - xb2))))
                && (!(((ya2 + yb2 - ya1 - yb1) <= (yb2 - ya1))
                || ((ya2 + yb2 - ya1 - yb1) <= (ya2 - yb1))));
    }







}
