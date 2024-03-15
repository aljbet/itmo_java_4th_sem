package Entities;

import Entities.Accounts.IAccount;
import lombok.NonNull;

public interface IBank
{
    void CreateClient(@NonNull String name, String address, String passport);
    void CreateCreditAccount(@NonNull String id, @NonNull IClient client);
    void CreateDebitAccount(@NonNull String id, @NonNull IClient client);
    void CreateDepositAccount(@NonNull String id, @NonNull IClient client, float startAmount);
    String Deposit(@NonNull String id, float amount);
    IAccount GetAccountById(@NonNull String id);
    IClient GetClientByName(@NonNull String name);
    String GetName();
    String Transfer(@NonNull String source, @NonNull String target, float amount);
    String Withdraw(@NonNull String id, float amount);
}
