package Entities.Accounts;

import Entities.IClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class CreditAccount implements IAccount
{
    private String _id;
    private float _balance = 0;
    private float _creditLimit;
    private CreditAccount _previousState = null;
    private IClient _owner;

    public CreditAccount(@NonNull String id, @NonNull IClient owner, float creditLimit)
    {
        _id = id;
        _owner = owner;
        _creditLimit = creditLimit;
    }

    @Override
    public void CancelTransaction()
    {
        this._balance = _previousState.get_balance();
        this._previousState = _previousState.get_previousState();
    }

    @Override
    public CreditAccount Clone()
    {
        return new CreditAccount(_id, _balance, _creditLimit, _previousState, _owner);
    }

    @Override
    public String Deposit(float amount)
    {
        _previousState = Clone();
        _balance += amount;
        return "Success. Current balance: " + _balance + ".\n";
    }

    @Override
    public String GetId()
    {
        return _id;
    }

    @Override
    public IClient GetOwner()
    {
        return _owner;
    }

    @Override
    public String Withdraw(float amount)
    {
        if (_balance - amount < 0 && amount - _balance > _creditLimit)
            return "Failed. Not enough money.\n";

        _previousState = Clone();
        _balance -= amount;
        return "Success. Current balance: " + _balance + ".\n";
    }
}
