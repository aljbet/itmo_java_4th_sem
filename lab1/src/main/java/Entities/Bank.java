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
    private final float _interestOnBalanceDebit;
    private final float _interestOnBalanceLowDeposit;
    private final float _interestOnBalanceHighDeposit;
    private final float _doubtSum;
    private List<IClient> _clients = new Vector<>();
    private List<IAccount> _accounts = new Vector<>();

    public Bank(
            @NonNull String name,
            float commission,
            float iobDebit,
            float iobLowDeposit,
            float iobHighDeposit,
            float doubtSum)
    {
        _name = name;
        _commission = commission;
        _interestOnBalanceDebit = iobDebit;
        _interestOnBalanceLowDeposit = iobLowDeposit;
        _interestOnBalanceHighDeposit = iobHighDeposit;
        _doubtSum = doubtSum;
    }

    @Override
    public String GetName()
    {
        return _name;
    }

    @Override
    public IClient GetClientByName(@NonNull String name)
    {
        for (IClient client : _clients) {
            if (client.GetFullName().equals(name)) return client;
        }

        return null;
    }

    @Override
    public IAccount GetAccountById(@NonNull String id)
    {
        for (IAccount account : _accounts) {
            if (account.GetId().equals(id)) return account;
        }

        return null;
    }

    @Override
    public void CreateClient(@NonNull String name, String address, String passport)
    {
        _clients.add(new Client(name, address, passport));
    }

    @Override
    public void CreateCreditAccount(@NonNull String id, @NonNull IClient client)
    {
        _accounts.add(new CreditAccount(id, client));
    }

    @Override
    public void CreateDebitAccount(@NonNull String id, @NonNull IClient client)
    {
        _accounts.add(new DebitAccount(id, client));
    }

    @Override
    public void CreateDepositAccount(@NonNull String id, @NonNull IClient client)
    {
        _accounts.add(new DepositAccount(id, client));
    }
}
