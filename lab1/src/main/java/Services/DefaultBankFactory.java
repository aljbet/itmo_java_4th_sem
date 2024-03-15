package Services;

import Entities.Bank;
import Entities.IBank;
import lombok.NonNull;

import java.util.Vector;

public class DefaultBankFactory implements IBankFactory
{
    private Vector<IBank> _banks;

    public DefaultBankFactory()
    {
        _banks = new Vector<>();
        _banks.add(new Bank("open", 0f, 0f, 0f, 0f, 100, 50, 10));
        _banks.add(new Bank("sber", 0f, 0f, 0f, 0f, 100, 50, 10));
    }

    @Override
    public void AddBank(@NonNull IBank bank)
    {
        _banks.add(bank);
    }

    @Override
    public Vector<IBank> GetAllBanks()
    {
        return _banks;
    }
}
