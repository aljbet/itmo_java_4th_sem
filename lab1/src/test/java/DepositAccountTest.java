import Entities.Accounts.DepositAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositAccountTest
{
    private final DepositAccount _account
            = new DepositAccount("1", 12000, 0,
            4, 5, null, null);

    @Test
    void deposit()
    {
        String expected = "Success. Current balance: 19800.0.\n";
        String actual = _account.Deposit(7800);
        assertEquals(expected, actual);
    }

    @Test
    void withdraw_fail_period()
    {
        String expected = "Failed. Deposit period is not over.\n";
        DepositAccount account = new DepositAccount("1", 12000, 3,
                4, 5, null, null);
        String actual = account.Withdraw(1800);
        assertEquals(expected, actual);
    }

    @Test
    void withdraw_fail()
    {
        String expected = "Failed. Not enough money.\n";
        String actual = _account.Withdraw(15000);
        assertEquals(expected, actual);
    }

    @Test
    void withdraw_success()
    {
        String expected = "Success. Current balance: 9500.0.\n";
        String actual = _account.Withdraw(2500);
        assertEquals(expected, actual);
    }
}
