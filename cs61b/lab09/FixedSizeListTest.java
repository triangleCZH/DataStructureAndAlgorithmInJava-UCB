import org.junit.Test;

import static org.junit.Assert.*;

public class FixedSizeListTest {

    @Test
    public void testSize() throws Exception {
        FixedSizeList a = new FixedSizeList(30);
        assertTrue(a.size()==0);
    }

    @Test
    public void testIsEmpty() throws Exception {
        FixedSizeList a = new FixedSizeList(30);
        assertTrue(a.isEmpty());
    }

    @Test
    public void testAdd() throws Exception {
        FixedSizeList a = new FixedSizeList(30);
        a.add(2);
        a.add(3);
        a.add(4);
        assertTrue(!a.isEmpty());
        assertTrue(a.size() == 3);
        assertTrue(a.values[2] == 4);
    }

    @Test
    public void testRemove() throws Exception {
        FixedSizeList a = new FixedSizeList(30);
        a.add(2);
        a.add(3);
        a.add(4);
        a.remove(3);
        assertTrue(a.values[1] == 0);
        a.remove(4);
        assertTrue(a.size() == 1);
        a.remove(2);
        assertTrue(a.isEmpty());
    }

    @Test
    public void testContains() throws Exception {
        FixedSizeList a = new FixedSizeList(30);
        a.add(2);
        a.add(3);
        a.add(4);
        assertTrue(a.contains(2));
        assertTrue(!a.contains(5));
    }

    @Test
    public void testGet() throws Exception {
        FixedSizeList a = new FixedSizeList(30);
        a.add(2);
        a.add(3);
        a.add(4);
        assertTrue(a.get(2) == 4);
    }

    @Test
    public void testAdd1() throws Exception {
        FixedSizeList a = new FixedSizeList(30);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(1,100);
        assertTrue(a.count == 4);
        assertTrue(a.get(1) == 100);
        assertTrue(a.get(3) == 4);
    }

    @Test
    public void testRemoveIndex() throws Exception {
        FixedSizeList a = new FixedSizeList(30);
        a.add(2);
        a.add(3);
        a.add(4);
        a.removeIndex(1);
        assertTrue(a.count == 2);
        assertTrue(a.get(1) == 4);

    }

    @Test
    public void testSize1() throws Exception {

    }
}