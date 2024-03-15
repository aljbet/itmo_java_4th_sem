import Entities.Accounts.CreditAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditAccountTest
{
    private final CreditAccount _account =
            new CreditAccount("1", 5000, 10000,
                    100, null, null);

    @Test
    void deposit()
    {
        String expected = "Success. Current balance: 17000.0.\n";
        String actual = _account.Deposit(12000);

        assertEquals(expected, actual);
    }

    @Test
    void withdraw_fail()
    {
        String expected = "Failed. Not enough money.\n";
        String actual = _account.Withdraw(20000);

        assertEquals(expected, actual);
    }

    @Test
    void withdraw_minusSuccess()
    {
        String expected = "Success. Current balance: -4000.0.\n";
        String actual = _account.Withdraw(9000);

        assertEquals(expected, actual);
    }

    @Test
    void withdraw_success()
    {
        String expected = "Success. Current balance: 4000.0.\n";
        String actual = _account.Withdraw(1000);

        assertEquals(expected, actual);
    }
}
