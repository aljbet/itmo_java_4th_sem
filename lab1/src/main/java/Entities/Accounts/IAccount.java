package Entities.Accounts;

import Entities.IClient;

public interface IAccount
{
    void CancelTransaction();
    String GetId();
    IClient GetOwner();
}
