package Services;

import Entities.Bank;
import Entities.IBank;

import java.util.List;
import java.util.Vector;

public class CentralBank implements ICentralBank
{
    IBankFactory _bankFactory = new DefaultBankFactory();

    @Override
    public List<String> GetAllBankNames()
    {
        Vector<String> res = new Vector<>();
        for (IBank bank : _bankFactory.GetAllBanks()) {
            res.add(bank.GetName());
        }

        return res;
    }

    @Override
    public IBank GetBank(String name)
    {
        for (IBank bank : _bankFactory.GetAllBanks()) {
            if (bank.GetName().equals(name)) return bank;
        }

        return null;
    }

    @Override
    public void CreateBank(String name, float commission, float iobDebit, float iobLowDeposit, float iobHighDeposit)
    {
        _bankFactory.AddBank(new Bank(name, commission, iobDebit, iobLowDeposit, iobHighDeposit));
    }
}
