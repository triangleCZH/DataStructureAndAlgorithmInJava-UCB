import static org.junit.Assert.*;

/**
 * Created by cs61bl-bk on 7/15/16.
 */
public class BinarySearchTreeTest {

    @org.junit.Test
    public void testContains() throws Exception {
        BinarySearchTree<Integer> a =new BinarySearchTree<>();

    }

    @org.junit.Test
    public void testContains1() throws Exception {

    }

    @org.junit.Test
    public void testAdd() throws Exception {
        BinarySearchTree<Integer> a =new BinarySearchTree<>();
        a.add(1);
        a.add(2);
        a.add(3);
        assertTrue(a.root.right.right.item == 3);
    }

    @org.junit.Test
    public void testAdd1() throws Exception {
        BinarySearchTree<Integer> a =new BinarySearchTree<>();
        a.add(1);
        a.add(2);
        a.add(3);
        assertTrue(a.root.right.right.item == 3);

    }
}