package Entities.Accounts;

import Entities.IClient;

public interface IAccount
{
    void CancelTransaction();
    IAccount Clone();
    String GetId();
    IClient GetOwner();

    String Withdraw(float amount);
    String Deposit(float amount);
}
