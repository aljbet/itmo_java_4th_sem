package Entities;

import Entities.Accounts.IAccount;
import lombok.NonNull;

public interface IBank
{
    void ChangeCreditLimit(float creditLimit);
    void ChangeInterestOnBalanceDebit(float iobDebit);
    void ChangeInterestOnBalanceLowDeposit(float iobLowDeposit);
    void ChangeInterestOnBalanceHighDeposit(float iobHighDeposit);
    void CreateClient(@NonNull String name, String address, String passport);
    void CreateClient(@NonNull IClient client);
    void CreateCreditAccount(@NonNull String id, @NonNull IClient client);
    void CreateDebitAccount(@NonNull String id, @NonNull IClient client);
    void CreateDepositAccount(@NonNull String id, @NonNull IClient client, float startAmount);
    String Deposit(@NonNull String id, float amount);
    void DepositDailyIOB();
    void DepositMonthlyIOB();
    IAccount GetAccountById(@NonNull String id);
    IClient GetClientByName(@NonNull String name);
    String GetName();
    void NotifySubscribers(@NonNull String message);
    String Transfer(@NonNull String source, @NonNull String target, float amount);
    String Withdraw(@NonNull String id, float amount);
    void WithdrawCommission();
}
