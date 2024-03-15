package Services;

import Entities.Bank;
import Entities.Client;
import Entities.IBank;
import lombok.NonNull;

import java.util.Vector;

public class DefaultBankFactory implements IBankFactory
{
    private final Vector<IBank> _banks;

    public DefaultBankFactory()
    {
        _banks = new Vector<>();
        Bank sber = new Bank("sber", 50, 50000, 20000, 3.5f, 5, 7, 75000, 10);
        Bank vtb = new Bank("vtb", 45, 40000, 50000, 4.5f, 3.5f, 5, 70000, 14);

        AddBank(sber);
        AddBank(vtb);

        Client ivan = new Client("Ivan Ivanov", "Moscow", "1234567890");
        Client petr = new Client("Petr Petrov", "", "");

        sber.CreateClient(ivan.GetFullName(), ivan.GetAddress(), ivan.GetPassport());
        sber.CreateClient(petr.GetFullName(), petr.GetAddress(), petr.GetPassport());
        vtb.CreateClient(ivan.GetFullName(), ivan.GetAddress(), ivan.GetPassport());
        vtb.CreateClient(petr.GetFullName(), petr.GetAddress(), petr.GetPassport());

        sber.CreateCreditAccount("i_cr_sber", ivan);
        sber.CreateDebitAccount("p_deb_sber", petr);
        sber.CreateDepositAccount("i_dep_sber", ivan, 70000);

        vtb.CreateCreditAccount("p_cr_vtb", petr);
        vtb.CreateDebitAccount("i_deb_vtb", ivan);
        vtb.CreateDepositAccount("p_dep_vtb", petr, 100000);
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
