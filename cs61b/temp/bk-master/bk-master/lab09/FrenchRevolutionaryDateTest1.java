import org.junit.Test;

import static org.junit.Assert.*;

public class FrenchRevolutionaryDateTest1 {

    @Test
    public void testNextDate() throws Exception {
        FrenchRevolutionaryDate a = new FrenchRevolutionaryDate(1,1,25);
        assertTrue(a.nextDate().toString().equals("26/1/1"));
        FrenchRevolutionaryDate b = new FrenchRevolutionaryDate(1,1,30);
        //System.out.println(b.nextDate().toString());
        assertTrue(b.nextDate().toString().equals("1/2/1"));
        FrenchRevolutionaryDate c = new FrenchRevolutionaryDate(1,12,30);
        //System.out.println(c.nextDate().toString());
        assertTrue(c.nextDate().toString().equals("1/13/1"));
        FrenchRevolutionaryDate d = new FrenchRevolutionaryDate(1,13,5);
        assertTrue(d.nextDate().toString().equals("1/1/2"));
        FrenchRevolutionaryDate e = new FrenchRevolutionaryDate(1,13,3);
        //System.out.println(e.nextDate().toString());
        assertTrue(e.nextDate().toString().equals("4/13/1"));

    }
}