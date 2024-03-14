package Entities;

import Entities.Accounts.IAccount;
import lombok.NonNull;

public interface IBank
{
    String GetName();

    IClient GetClientByName(@NonNull String name);
    IAccount GetAccountById(@NonNull String id);

    void CreateClient(@NonNull String name, String address, String passport);

    void CreateCreditAccount(@NonNull String id, @NonNull IClient client);
    void CreateDebitAccount(@NonNull String id, @NonNull IClient client);
    void CreateDepositAccount(@NonNull String id, @NonNull IClient client);
}
