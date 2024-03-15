package Entities.Accounts;

import Entities.IClient;

public interface IAccount
{
    void CancelTransaction();
    IAccount Clone();
    String Deposit(float amount);
    void DepositDailyIOB();
    void DepositMonthlyIOB();
    float GetBalance();
    String GetId();
    IClient GetOwner();
    void SetCreditLimit(float creditLimit);
    void SetIOB(float iob);
    String Withdraw(float amount);
    void WithdrawCommission();
}
