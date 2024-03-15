package Entities;

import Entities.Accounts.CreditAccount;
import Entities.Accounts.DebitAccount;
import Entities.Accounts.DepositAccount;
import Entities.Accounts.IAccount;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.Vector;

@Getter
public class Bank implements IBank
{
    private final String _name;
    private final float _commission;
    private final float _doubtSum;
    private float _creditLimit;
    private float _interestOnBalanceDebit;
    private float _interestOnBalanceLowDeposit;
    private float _interestOnBalanceHighDeposit;
    private final float _depositBorder;
    private final int _depositPeriod;
    private final List<IClient> _clients = new Vector<>();
    private final List<IClient> _subscribers = new Vector<>();
    private final List<IAccount> _accounts = new Vector<>();

    public Bank(
            @NonNull String name,
            float commission,
            float doubtSum,
            float creditLimit,
            float iobDebit,
            float iobLowDeposit,
            float iobHighDeposit,
            float depositBorder,
            int depositPeriod)
    {
        _name = name;
        _commission = commission;
        _doubtSum = doubtSum;
        _creditLimit = creditLimit;
        _interestOnBalanceDebit = iobDebit;
        _interestOnBalanceLowDeposit = iobLowDeposit;
        _interestOnBalanceHighDeposit = iobHighDeposit;
        _depositBorder = depositBorder;
        _depositPeriod = depositPeriod;
    }

    @Override
    public void ChangeCreditLimit(float creditLimit)
    {
        _creditLimit = creditLimit;
        for (IAccount account : _accounts)
        {
            if (account instanceof CreditAccount)
            {
                account.SetCreditLimit(creditLimit);
            }
        }
        NotifySubscribers("Credit limit changed to " + creditLimit + ".\n");
    }

    @Override
    public void ChangeInterestOnBalanceDebit(float iobDebit)
    {
        _interestOnBalanceDebit = iobDebit;
        for (IAccount account : _accounts)
        {
            if (account instanceof DebitAccount)
            {
                account.SetIOB(iobDebit);
            }
        }
        NotifySubscribers("Interest in balance for debit accounts changed to " + iobDebit + ".\n");
    }

    @Override
    public void ChangeInterestOnBalanceLowDeposit(float iobLowDeposit)
    {
        _interestOnBalanceLowDeposit = iobLowDeposit;
        for (IAccount account : _accounts)
        {
            if (account instanceof DepositAccount && account.GetBalance() < _depositBorder)
            {
                account.SetIOB(iobLowDeposit);
            }
        }
        NotifySubscribers("Low interest in balance for deposit accounts changed to " + iobLowDeposit + ".\n");
    }

    @Override
    public void ChangeInterestOnBalanceHighDeposit(float iobHighDeposit)
    {
        _interestOnBalanceHighDeposit = iobHighDeposit;
        for (IAccount account : _accounts)
        {
            if (account instanceof DepositAccount && account.GetBalance() >= _depositBorder)
            {
                account.SetIOB(iobHighDeposit);
            }
        }
        NotifySubscribers("High interest in balance for deposit accounts changed to " + iobHighDeposit + ".\n");
    }

    @Override
    public void CreateClient(@NonNull String name, String address, String passport)
    {
        _clients.add(new Client(name, address, passport));
    }

    @Override
    public void CreateClient(@NonNull IClient client)
    {
        _clients.add(client);
    }

    @Override
    public void CreateCreditAccount(@NonNull String id, @NonNull IClient client)
    {
        _accounts.add(new CreditAccount(id, client, _creditLimit, _commission));
    }

    @Override
    public void CreateDebitAccount(@NonNull String id, @NonNull IClient client)
    {
        _accounts.add(new DebitAccount(id, client, _interestOnBalanceDebit));
    }

    @Override
    public void CreateDepositAccount(@NonNull String id, @NonNull IClient client, float startAmount)
    {
        if (startAmount < _depositBorder)
            _accounts.add(new DepositAccount(id, client, _depositPeriod, _interestOnBalanceLowDeposit));
        else
            _accounts.add(new DepositAccount(id, client, _depositPeriod, _interestOnBalanceHighDeposit));
    }

    @Override
    public String Deposit(@NonNull String id, float amount)
    {
        return GetAccountById(id).Deposit(amount);
    }

    @Override
    public void DepositDailyIOB()
    {
        for (IAccount account : _accounts)
        {
            if (account instanceof DebitAccount || account instanceof DepositAccount)
            {
                account.DepositDailyIOB();
            }
        }
    }

    @Override
    public void DepositMonthlyIOB()
    {
        for (IAccount account : _accounts)
        {
            if (account instanceof DebitAccount || account instanceof DepositAccount)
            {
                account.DepositMonthlyIOB();
            }
        }
    }

    @Override
    public IAccount GetAccountById(@NonNull String id)
    {
        for (IAccount account : _accounts)
        {
            if (account.GetId().equals(id)) return account;
        }

        return null;
    }

    @Override
    public IClient GetClientByName(@NonNull String name)
    {
        for (IClient client : _clients)
        {
            if (client.GetFullName().equals(name)) return client;
        }

        return null;
    }

    @Override
    public String GetName()
    {
        return _name;
    }

    @Override
    public void NotifySubscribers(@NonNull String message)
    {
        for (IClient client : _subscribers)
        {
            client.ReceiveMessage(message);
        }
    }

    @Override
    public String Transfer(@NonNull String source, @NonNull String target, float amount)
    {
        String first = GetAccountById(source).Withdraw(amount);
        if (first.charAt(0) == 'F') return first;
        GetAccountById(target).Deposit(amount);
        return "Success.\n";
    }

    @Override
    public String Withdraw(@NonNull String id, float amount)
    {
        IAccount account = GetAccountById(id);
        if ((account.GetOwner().GetAddress().isEmpty() || account.GetOwner().GetPassport().isEmpty())
                && amount > _doubtSum)
            return "Failed. Confirm address and passport first.\n";
        return GetAccountById(id).Withdraw(amount);
    }

    @Override
    public void WithdrawCommission()
    {
        for (IAccount account : _accounts)
        {
            if (account instanceof CreditAccount)
            {
                account.WithdrawCommission();
            }
        }
    }
}
