package Entities.Accounts;

import Entities.IClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class DebitAccount implements IAccount
{
    private String _id;
    private float _balance = 0;
    private DebitAccount _previousState = null;
    private IClient _owner;

    public DebitAccount(@NonNull String id, @NonNull IClient owner)
    {
        _id = id;
        _owner = owner;
    }

    @Override
    public void CancelTransaction()
    {
        if (_previousState != null)
        {
            _balance = _previousState.get_balance();
            _previousState = _previousState.get_previousState();
        }
    }

    @Override
    public DebitAccount Clone()
    {
        return new DebitAccount(_id, _balance, _previousState, _owner);
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
        if (_balance - amount < 0)
            return "Failed. Not enough money.\n";

        _previousState = Clone();
        _balance -= amount;
        return "Success. Current balance: " + _balance + ".\n";
    }
}
