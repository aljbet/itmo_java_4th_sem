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
    private float _interestOnBalance;
    private float _currentIOBSum = 0;
    private DebitAccount _previousState = null;
    private IClient _owner;

    public DebitAccount(@NonNull String id, @NonNull IClient owner, float interestOnBalance)
    {
        _id = id;
        _owner = owner;
        _interestOnBalance = interestOnBalance;
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
        return new DebitAccount(_id, _balance, _interestOnBalance, _currentIOBSum, _previousState, _owner);
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
        if (_balance - amount < 0)
            return "Failed. Not enough money.\n";

        _previousState = Clone();
        _balance -= amount;
        return "Success. Current balance: " + _balance + ".\n";
    }

    @Override
    public void WithdrawCommission() {}
}
