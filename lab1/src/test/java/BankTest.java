import Entities.Accounts.DebitAccount;
import Entities.Client;
import Entities.IBank;
import Services.CentralBank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest
{
    private final IBank _bank = new CentralBank().GetBankByName("vtb");

    @Test
    public void deposit()
    {
        String expected = "Success. Current balance: 500.0.\n";

        String account = "p_cr_vtb";
        float amount = 500;

        String actual = _bank.Deposit(account, amount);

        assertEquals(expected, actual);
    }

    @Test
    void getAccountById()
    {
        var expected = new DebitAccount("i_deb_vtb", 0, 4.5f,
                0, null, _bank.GetClientByName("Ivan Ivanov"));
        var actual = _bank.GetAccountById("i_deb_vtb");

        assertEquals(expected.GetBalance(), ((DebitAccount) actual).GetBalance());
        assertEquals(expected.get_interestOnBalance(), ((DebitAccount) actual).get_interestOnBalance());
        assertEquals(expected.get_currentIOBSum(), ((DebitAccount) actual).get_currentIOBSum());
        assertEquals(expected.get_previousState(), ((DebitAccount) actual).get_previousState());
        assertEquals(expected.get_owner(), ((DebitAccount) actual).get_owner());
    }

    @Test
    void getClientByName()
    {
        var expected = new Client("Ivan Ivanov", "Moscow", "1234567890");
        var actual = _bank.GetClientByName("Ivan Ivanov");

        assertEquals(expected.GetAddress(), actual.GetAddress());
        assertEquals(expected.GetPassport(), actual.GetPassport());
    }

    @Test
    void transfer_success()
    {
        String expected = "Success.\n";

        String source = "p_cr_vtb";
        String target = "i_deb_vtb";
        float amount = 5000;

        String actual = _bank.Transfer(source, target, amount);

        assertEquals(expected, actual);
    }

    @Test
    void withdraw_failDoubtSum()
    {
        String expected = "Failed. Confirm address and passport first.\n";

        String account = "p_cr_vtb";
        float amount = 45000;

        String actual = _bank.Withdraw(account, amount);

        assertEquals(expected, actual);
    }

    @Test
    void withdraw_success()
    {
        String expected = "Success. Current balance: -5000.0.\n";

        String account = "p_cr_vtb";
        float amount = 5000;

        String actual = _bank.Withdraw(account, amount);

        assertEquals(expected, actual);
    }

}
