import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

/**
 * A BinaryTree is a tree with nodes that have up to two children.
 */
public class BinaryTree<T> implements Iterable<T>{

    public Iterator<T> iterator() {
        return new treeiterator<>();
    }
    public class treeiterator<T> implements Iterator<T>{
     public T next(){
         return null;
     }
        public boolean hasNext(){
            return false;
        }
        public treeiterator() {
            int a = 0;
        }
    }

    /**
     * root is the root of this BinaryTree
     */
    private TreeNode root;

    /**
     * The BinaryTree constructor
     */
    public BinaryTree() {
        root = null;
    }

    public BinaryTree(TreeNode t) {
        root = t;
    }

    /**
     * Print the values in the tree in preorder: root value first, then values
     * in the left subtree (in preorder), then values in the right subtree
     * (in preorder).
     */
    public void printPreorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printPreorder();
            System.out.println();
        }
    }

    public int height() {
     return  height(root);
    }

    public boolean isCompletelyBalanced() {
       return isCompletelyBalanced(root);
    }

    public boolean isCompletelyBalanced(TreeNode t) {
        if(t == null) {
            return true;
        } else if(((t.left == null) && (t.right != null)) || ((t.right == null) && (t.left != null))) {
            return false;
        } else {
           return (isCompletelyBalanced(t.left) && isCompletelyBalanced(t.right));
        }
    }

    public int height(TreeNode t) {
        if (t == null) {
            return 0;
        } else  {
            return Math.max(height(t.left)+1,height(t.right)+1);
        }
    }

    /**
     * Print the values in the tree in inorder: values in the left subtree
     * first (in inorder), then the root value, then values in the first
     * subtree (in inorder).
     */
    public void printInorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printInorder();
            System.out.println();
        }
    }

    /**
     * Fills this BinaryTree with values a, b, and c
     */
    public void fillSampleTree1() {
        root = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    /**
     * Fills this BinaryTree with values a, b, and c, d, e, f
     */
    public void fillSampleTree2() {
        root = new TreeNode("a", new TreeNode("b", new TreeNode("d",
            new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }

    public void fillSampleTree3() {
        root = new TreeNode("a",new TreeNode("b",null,null),new TreeNode("c",new TreeNode("d",new TreeNode("e"),new TreeNode("f")),null));
    }

    public void print() {
        if (root != null) {
            root.print(0);
        }
    }

    private void isOK(TreeNode t) throws IllegalStateException{
        ArrayList<TreeNode> fringe = new ArrayList<>();

    }


    /**
     * Creates two BinaryTrees and prints them out in inorder
     */
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        print(t, "sample tree 1");
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree3();
        print(t, "sample tree 3");
        t.print();
        System.out.println(t.isCompletelyBalanced());
    }

    /**
     * Prints out the contents of a BinaryTree with a description in both
     * preorder and inorder
     * @param t           the BinaryTree to print out
     * @param description a String describing the BinaryTree t
     */
    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    /**
     * A TreeNode is a Node this BinaryTree
     */
    private static class TreeNode {

        /**
         * item is the item that is contained in this TreeNode
         * left is the left child of this TreeNode
         * right is the right child of this TreeNode
         */
        public Object item;
        public TreeNode left;
        public TreeNode right;

        /**
         * A TreeNode constructor that creates a node with obj as its item
         * @param  obj the item to be contained in this TreeNode
         */
        TreeNode(Object obj) {
            item = obj;
            left = null;
            right = null;
        }


        //In TreeNode
        private static final String indent1 = "    ";

        private void print(int indent) {
            if (this.right != null)
            this.right.print(indent+1);
            // TODO your code here
            println (item, indent);
            // TODO your code here
            if (this.left != null)
            this.left.print(indent+1);
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }

        /**
         * A Treenode constructor that creates a node with obj as its item and
         * left and right as its children
         * @param  obj   the item to be contained in this TreeNode
         * @param  left  the left child of this TreeNode
         * @param  right the right child of this TreeNode
         */
        TreeNode(Object obj, TreeNode left, TreeNode right) {
            item = obj;
            this.left = left;
            this.right = right;
        }

        /**
         * Prints this TreeNode and the subtree rooted at it in preorder
         */
        private void printPreorder() {
            System.out.print(item + " ");
            if (left != null) {
                left.printPreorder();
            }
            if (right != null) {
                right.printPreorder();
            }
        }

        /**
         * Prints this TreeNode and the subtree rooted at it in inorder
         */
        private void printInorder() {
            if (left != null) {
                left.printInorder();
            }
            System.out.print(item + " ");
            if (right != null) {
                right.printInorder();
            }
        }
    }
}
