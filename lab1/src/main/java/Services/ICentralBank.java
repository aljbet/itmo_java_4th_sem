package Services;

import Entities.IBank;
import Entities.IClient;
import lombok.NonNull;

import java.util.List;

public interface ICentralBank
{
    void CreateBank(@NonNull String name,
                    float commission,
                    float doubtSum,
                    float creditLimit,
                    float iobDebit,
                    float iobLowDeposit,
                    float iobHighDeposit,
                    float depositBorder,
                    int depositPeriod);

    void DepositDailyIOB();

    void DepositMonthlyIOB();

    List<String> GetAllBankNames();

    IBank GetBankByName(@NonNull String name);

    String InterbankTransfer(@NonNull IClient client,
                             @NonNull String sourceBankName,
                             @NonNull String sourceId,
                             @NonNull String targetBankName,
                             @NonNull String targetId,
                             float amount);

    void WithdrawCommission();
}
