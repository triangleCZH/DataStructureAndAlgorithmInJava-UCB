import org.junit.Test;

import static org.junit.Assert.*;

public class GregorianDateTest {

    @Test
    public void testNextDate() throws Exception {
        GregorianDate a = new GregorianDate(1,1,1);
        assertTrue(a.nextDate().toString().equals("2/1/1"));
        GregorianDate b = new GregorianDate(1,1,31);
        assertTrue(b.nextDate().toString().equals("1/2/1"));
        GregorianDate c = new GregorianDate(1,12,31);
        assertTrue(c.nextDate().toString().equals("1/1/2"));

    }
}