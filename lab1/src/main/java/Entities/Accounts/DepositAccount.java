package Entities.Accounts;

import Entities.IClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class DepositAccount implements IAccount
{
    private String _id;
    private float _balance = 0;
    private int _depositPeriod;
    private float _interestOnBalance;
    private float _currentIOBSum = 0;
    private DepositAccount _previousState = null;
    private IClient _owner;

    public DepositAccount(@NonNull String id, @NonNull IClient owner, int depositPeriod, float interestOnBalance)
    {
        _id = id;
        _owner = owner;
        _depositPeriod = depositPeriod;
        _interestOnBalance = interestOnBalance;
    }

    @Override
    public void CancelTransaction()
    {
        this._balance = _previousState.get_balance();
        this._previousState = _previousState.get_previousState();
    }

    @Override
    public DepositAccount Clone()
    {
        return new DepositAccount(_id, _balance, _depositPeriod, _interestOnBalance, _currentIOBSum, _previousState, _owner);
    }

    @Override
    public String Deposit(float amount)
    {
        _previousState = Clone();
        _balance += amount;
        return "Success. Current balance: " + _balance + ".\n";
    }

    @Override
    public void DepositDailyIOB()
    {
        _currentIOBSum += (_interestOnBalance * _balance) / (100 * 365);
    }

    @Override
    public void DepositMonthlyIOB()
    {
        _balance += _currentIOBSum;
        _currentIOBSum = 0;
    }

    @Override
    public float GetBalance()
    {
        return _balance;
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
    public void SetCreditLimit(float creditLimit)
    {
    }

    @Override
    public void SetIOB(float iob)
    {
        _interestOnBalance = iob;
    }

    @Override
    public String Withdraw(float amount)
    {
        if (_depositPeriod > 0)
            return "Failed. Deposit period is not over.\n";

        if (_balance - amount < 0)
            return "Failed. Not enough money.\n";

        _previousState = Clone();
        _balance -= amount;
        return "Success. Current balance: " + _balance + ".\n";
    }

    @Override
    public void WithdrawCommission() {}
}
