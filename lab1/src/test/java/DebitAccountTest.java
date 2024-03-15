import Entities.Accounts.DebitAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitAccountTest
{
    private final DebitAccount _account =
            new DebitAccount("1", 5000, 5, 0, null, null);
    @Test
    void deposit()
    {
        String expected = "Success. Current balance: 7500.0.\n";
        String actual = _account.Deposit(2500);
        assertEquals(expected, actual);
    }

    @Test
    void withdraw_fail()
    {
        String expected = "Failed. Not enough money.\n";
        String actual = _account.Withdraw(10000);
        assertEquals(expected, actual);
    }

    @Test
    void withdraw_success()
    {
        String expected = "Success. Current balance: 2500.0.\n";
        String actual = _account.Withdraw(2500);
        assertEquals(expected, actual);
    }
}
