import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @org.junit.Test
    public void testGetBalance() throws Exception {
        Account a = new Account(2, null);
        assertTrue(a.getBalance() == 2);
    }

    @Test
    public void testDeposit() throws Exception {
        Account a = new Account(2, null);
        a.deposit(-1);
        assertTrue((a.getBalance() == 2));
        Account c = new Account(2, null);
        c.deposit(1);
        assertTrue((c.getBalance() == 3));

    }

    @Test
    public void testWithdraw() throws Exception {
        Account a = new Account(2, null);
        a.withdraw(3);
        assertTrue((a.getBalance() == 2));
        Account d = new Account(2, null);
        d.withdraw(-1);
        assertTrue((d.getBalance() == 2));
        Account c = new Account(3, null);
        c.withdraw(1);
        assertTrue((c.getBalance() == 2));
    }
}