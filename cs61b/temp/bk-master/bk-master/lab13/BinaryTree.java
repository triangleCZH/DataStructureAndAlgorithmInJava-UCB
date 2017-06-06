import java.util.*;

public class BinaryTree implements Iterable<BinaryTree.TreeNode>{//implement iterable
    /*
    * I want what to be iterable? implement that datatype in <>*/

    private TreeNode root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(TreeNode t) {
        root = t;
    }

    public TreeNode getRoot() {
        return root;
    }

    // Print the values in the tree in preorder: root value first,
    // then values in the left subtree (in preorder), then values
    // in the right subtree (in preorder).
    public void printPreorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printPreorder();
            System.out.println();
        }
    }

    // Print the values in the tree in inorder: values in the left
    // subtree first (in inorder), then the root value, then values
    // in the right subtree (in inorder).
    public void printInorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printInorder();
            System.out.println();
        }
    }

    public void fillSampleTree1() {
        TreeNode temp = new TreeNode("a");
        root = new TreeNode("a", temp, temp);
    }

    public void fillSampleTree2() {
        root = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }

    public void print() {
        if (root != null) {
            root.print(0);
        }
    }

    public boolean check() {
        alreadySeen = new ArrayList();
        try {
            isOK(root);
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    // Contains nodes already seen in the traversal.
    private ArrayList alreadySeen;


    private void isOK(TreeNode t) throws IllegalStateException {
        Iterator<TreeNode> e = iterator();
     //   int count = 0;
        while (e.hasNext()) {
            TreeNode curr = e.next();

            if (alreadySeen.contains(curr)) {//from the ArrayList library
                throw new IllegalStateException("Opppps!!! You seem to come to the wrong place");//throw exception in this
            }
            alreadySeen.add(curr);
        }

    }

    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.root = result.fibTreeHelper(n);
        return result;
    }
    private TreeNode fibTreeHelper(int n){

        if(n==0){
            return new  TreeNode(0);
        }
        if(n==1){
            return new  TreeNode(1);
        }
        TreeNode left = fibTreeHelper(n-1);
        TreeNode right = fibTreeHelper(n-2);
        TreeNode result = new TreeNode((Integer)left.getItem()+(Integer)right.getItem());
        result.left=left;
        result.right=right;
        return result;
    }

    public static BinaryTree exprTree(String s) {
        BinaryTree result = new BinaryTree();
        result.root = result.exprTreeHelper(s);
        return result;
    }
//    Return the tree corresponding to the given arithmetic expression.
// The expression is legal, fully parenthesized, contains no blanks,
// and involves only the operations + and *.
    private TreeNode exprTreeHelper(String expr) {


        if (expr.charAt(0) != '(') {
            return new TreeNode(expr);
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
                char ch = expr.charAt(k);

                if (ch == '(') {
                    nesting++;
                }
                if (ch == ')') {
                    nesting--;
                }
                if (nesting == 0 && (ch == '+' || ch == '*')) {
                    //                TreeNode t =new TreeNode(ch);
                    opPos = k;
                    break;
                }

            }
            String opnd1 = expr.substring(1, opPos);
            String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
            String op = expr.substring(opPos, opPos + 1);
            System.out.println("expression = " + expr);
            System.out.println("operand 1  = " + opnd1);
            System.out.println("operator   = " + op);
            System.out.println("operand 2  = " + opnd2);
            System.out.println();
            exprTreeHelper(opnd1);
            TreeNode t = new TreeNode(op);
            t.left=exprTreeHelper(opnd1);
            t.right=exprTreeHelper(opnd2);
            return t;
        }


    }

    public void optimize() { // always "pull" the this in the function so that easier to use
        optimizeHelper(root);
    }

    /** Optimize EXPR.*/
    private static void optimizeHelper(TreeNode expr) {
        if (expr == null) {
            return;
        }

        if (expr.left == null && expr.right == null) {
            return;
        }

        if (isOperator((String)expr.left.item)) {
            optimizeHelper(expr.left);
        }

        if (isOperator((String)expr.right.item)) {
            optimizeHelper(expr.right);
        }

        if ((isInteger((String)expr.left.item) && isInteger((String)expr.right.item))) {
            int re = performOperand((String)expr.item,
                    Integer.parseInt((String)expr.left.item),
                    Integer.parseInt((String)expr.right.item));

            expr.item = ""+re;
            expr.left = null;
            expr.right = null;
        }
    }

    /** Returns the integer from performing OP in VAL1 and VAL2.*/
    private static int performOperand(String op, int val1, int val2) {
        if (op.equals("+")) {
            return val1 + val2;
        }

        if (op.equals("*")) {
            return val1 * val2;
        }

        return -1;
    }

    /** Returns iff STR is an integer.*/
    private static boolean isInteger(String str) {
        try {
            int k = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /** Returns iff STR is an operator..*/
    private static boolean isOperator(String str) {
        return str.equals("*") || str.equals("+");
    }



    public Iterator<TreeNode> iterator() {//origin constructor, calling the new constructor , and with the <>type
        return new TreeNodeIterator();
    }


    public class TreeNodeIterator implements Iterator { // where we  construct the iterator for TreeNode

        // Where we will store our bookmarks
        private Queue<TreeNode> fringe = new LinkedList<>();//first in first out, only add to behind

        public TreeNodeIterator() {// the first step of constructing is to include the root
            if (root != null) {
                fringe.add (root);
            }
        }

        // Returns whether or not we have any more "bookmarks" to visit
        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        /** Throws an exception if we have no more nodes to visit in our traversal. Otherwise,
         it picks the most recent entry to our stack and "explores" it. Exploring it requires
         visiting the node, adding its children to the fringe, since we must eventually visit
         them too. */
        public TreeNode next() { // you don't need to remove by yourself because the iterator can record where you currently is
//            if (!hasMoreElements()) {
//                throw new NoSuchElementException("tree ran out of elements");
//            }
            TreeNode node = fringe.remove();
            if (node.right != null) {
                fringe.add(node.right);
            }
            if (node.left != null) {
                fringe.add(node.left);
            }
            return node;
        }

        // We've decided not to use it for this example
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        print(t, "sample tree 1");
        t.fillSampleTree2();
        print(t, "sample tree 2");

    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    public static class TreeNode {

        public Object item;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(Object obj) {
            item = obj;
            left = right = null;
        }

        public TreeNode(Object obj, TreeNode left, TreeNode right) {
            item = obj;
            this.left = left;
            this.right = right;
        }

        private void printPreorder() {
            System.out.print(item + " ");
            if (left != null) {
                left.printPreorder();
            }
            if (right != null) {
                right.printPreorder();
            }
        }

        private void printInorder() {
            if (left != null) {
                left.printInorder();
            }
            System.out.print(item + " ");
            if (right != null) {
                right.printInorder();
            }
        }


        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }

        public Object getItem() {
            return item;
        }


        private static final String indent1 = "    ";

        private void print(int indent) {
                printhelp(this, indent);
        }

        private TreeNode printhelp(TreeNode Node, int indent){ //the excellent recursive idea
            if(Node.getLeft() != null){
                printhelp(Node.getLeft(), indent+1);
            }
            println (Node, indent);
            if(Node.getRight() != null){
                printhelp(Node.getRight(), indent+1);
            }
            return Node;
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
    }
}
