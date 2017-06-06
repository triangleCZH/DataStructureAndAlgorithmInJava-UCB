import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cs61bl-cc on 7/2/16.
 */
public class MeasurementTest {

    @org.junit.Test
    public void testConstructor1() {
        Measurement n = new Measurement();
        assertTrue(n.getFeet() == 0);
        assertTrue(n.getInches() == 0);
    }

    public void testConstructor2() {
        Measurement n = new Measurement(5);
        assertTrue(n.getFeet() == 5);
        assertTrue(n.getInches() == 0);
    }

    public void testConstructor3() {
        Measurement n = new Measurement(4, 3);
        assertTrue(n.getFeet() == 4);
        assertTrue(n.getInches() == 3);
    }


    @org.junit.Test
    public void testGetFeet() throws Exception {
        Measurement n = new Measurement(4, 5);
        assertTrue(n.getFeet() == 4);
    }

    @Test
    public void testGetInches() throws Exception {
        Measurement n = new Measurement(5, 1);
        assertTrue(n.getInches() == 1);
    }

    @Test
    public void testPlus() throws Exception {
        Measurement n = new Measurement(3, 11);
        Measurement m = new Measurement(2, 1);
        assertTrue(n.plus(m).getFeet() == 6 && n.plus(m).getInches() == 0);
    }

    @Test
    public void testMinus() throws Exception {
        Measurement n = new Measurement(2, 1);
        Measurement m = new Measurement(1, 9);
        assertTrue(n.minus(m).getFeet() == 0 && n.minus(m).getInches() == 4);
    }

    @Test
    public void testMultiple() throws Exception {
        Measurement n = new Measurement(1, 10);
        int b = 2;
        assertTrue(n.multiple(b).getInches() == 8 && n.multiple(b).getFeet() == 3);
    }


    public void TestString() throws Exception {
        Measurement n = new Measurement(8, 9);
        assertTrue(n.toString().equals("8 \' 9\" "));
    }
}