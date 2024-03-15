package Entities.Accounts;

import Entities.IClient;

public interface IAccount
{
    void CancelTransaction();
    IAccount Clone();
    String Deposit(float amount);
    String GetId();
    IClient GetOwner();
    String Withdraw(float amount);
}
