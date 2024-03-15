package Services;

import Entities.Accounts.IAccount;
import Entities.Bank;
import Entities.IBank;
import Entities.IClient;
import lombok.NonNull;

import java.util.List;
import java.util.Vector;

public class CentralBank implements ICentralBank
{
    IBankFactory _bankFactory = new DefaultBankFactory();

    @Override
    public void CreateBank(@NonNull String name,
                           float commission,
                           float doubtSum,
                           float creditLimit,
                           float iobDebit,
                           float iobLowDeposit,
                           float iobHighDeposit,
                           float depositBorder,
                           int depositPeriod)
    {
        _bankFactory.AddBank(new Bank(name, commission, doubtSum, creditLimit,
                iobDebit, iobLowDeposit, iobHighDeposit, depositBorder, depositPeriod));
    }

    @Override
    public void DepositDailyIOB()
    {
        for (IBank bank : _bankFactory.GetAllBanks()) bank.DepositDailyIOB();
    }

    @Override
    public void DepositMonthlyIOB()
    {
        for (IBank bank : _bankFactory.GetAllBanks()) bank.DepositMonthlyIOB();
    }

    @Override
    public List<String> GetAllBankNames()
    {
        Vector<String> res = new Vector<>();
        for (IBank bank : _bankFactory.GetAllBanks())
        {
            res.add(bank.GetName());
        }

        return res;
    }

    @Override
    public IBank GetBankByName(@NonNull String name)
    {
        for (IBank bank : _bankFactory.GetAllBanks())
        {
            if (bank.GetName().equals(name)) return bank;
        }

        return null;
    }

    @Override
    public String InterbankTransfer(@NonNull IClient client,
                                    @NonNull String sourceBankName,
                                    @NonNull String sourceId,
                                    @NonNull String targetBankName,
                                    @NonNull String targetId,
                                    float amount)
    {
        IBank sourceBank = GetBankByName(sourceBankName);
        IBank targetBank = GetBankByName(targetBankName);

        IAccount sourceAccount = sourceBank.GetAccountById(sourceId);
        IAccount targetAccount = targetBank.GetAccountById(targetId);

        if (sourceAccount == null || sourceAccount.GetOwner() != client)
            return "Failed. We couldn't find source account. Please try again.\n";
        if (targetAccount == null) return "Failed. We couldn't find target account. Please try again.\n";
        String first = sourceAccount.Withdraw(amount);
        if (first.charAt(0) == 'F') return first;
        targetAccount.Deposit(amount);
        return "Success.\n";
    }

    @Override
    public void WithdrawCommission()
    {
        for (IBank bank : _bankFactory.GetAllBanks()) bank.WithdrawCommission();
    }
}
