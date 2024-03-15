import Entities.Bank;
import Services.CentralBank;
import Services.ICentralBank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CentralBankTest
{
    private final ICentralBank _centralBank = new CentralBank();
    @Test
    void getAllBankNames()
    {
        var expected = new Vector<String>();
        expected.add("sber");
        expected.add("vtb");
        var actual = _centralBank.GetAllBankNames();

        assertEquals(expected, actual);
    }

    @Test
    void getBankByName()
    {
        var expected = new Bank("sber", 50, 50000, 20000, 3.5f, 5, 7, 75000, 10);
        var actual = _centralBank.GetBankByName("sber");

        assertEquals(expected.get_commission(), ((Bank) actual).get_commission());
        assertEquals(expected.get_doubtSum(), ((Bank) actual).get_doubtSum());
        assertEquals(expected.get_creditLimit(), ((Bank) actual).get_creditLimit());
        assertEquals(expected.get_interestOnBalanceDebit(), ((Bank) actual).get_interestOnBalanceDebit());
        assertEquals(expected.get_interestOnBalanceLowDeposit(), ((Bank) actual).get_interestOnBalanceLowDeposit());
        assertEquals(expected.get_interestOnBalanceHighDeposit(), ((Bank) actual).get_interestOnBalanceHighDeposit());
        assertEquals(expected.get_depositBorder(), ((Bank) actual).get_depositBorder());
        assertEquals(expected.get_depositPeriod(), ((Bank) actual).get_depositPeriod());

    }

    @ParameterizedTest
    @ValueSource(strings = {"p_cr_vtb", "p_dep_vtb"})
    void interbankTransfer_sourceIdDoesNotMatchClient(String sourceId)
    {
        String expected = "Failed. We couldn't find source account. Please try again.\n";

        String sourceBankName = "vtb";
        String targetBankName = "sber";
        var client = _centralBank.GetBankByName(sourceBankName).GetClientByName("Ivan Ivanov");
        String targetId = "i_cr_sber";
        float amount = 500;

        String actual = _centralBank.InterbankTransfer(client, sourceBankName, sourceId, targetBankName, targetId, amount);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "__vtb"})
    void interbankTransfer_targetAccountNotFound(String targetId)
    {
        String expected = "Failed. We couldn't find target account. Please try again.\n";

        String sourceBankName = "sber";
        String targetBankName = "vtb";
        var client = _centralBank.GetBankByName(sourceBankName).GetClientByName("Ivan Ivanov");
        String sourceId = "i_cr_sber";
        float amount = 500;

        String actual = _centralBank.InterbankTransfer(client, sourceBankName, sourceId, targetBankName, targetId, amount);

        assertEquals(expected, actual);
    }
}