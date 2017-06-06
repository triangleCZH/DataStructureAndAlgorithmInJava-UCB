import org.junit.Test;

import static org.junit.Assert.*;

public class FixedSizeListTestSIze {

    @Test
    public void testSize() throws Exception {
FixedSizeList a = new FixedSizeList(30);
        a.add(1);
        assertTrue(a.size() == 1);
        a.add(1,2);
        assertTrue(a.size() == 2);
        a.add(3);
        assertTrue(a.size() == 3);
        a.add(4);
        assertTrue(a.size() == 4);
        a.remove(2);
        assertTrue(a.size() == 3);
        a.removeIndex(2);
        assertTrue(a.size() == 2);
        a.add(3);
        assertTrue(a.size() == 3);
        a.remove(3);
        assertTrue(a.size() == 2);
        a.removeIndex(1);
        assertTrue(a.size() == 1);
        a.remove(1);
        assertTrue(a.size() == 0);
        FixedSizeList b = new FixedSizeList(3);
        b.add(0,3);
        b.add(0,2);
        b.add(0,1);
        System.out.println(b.get(2));

    }
}