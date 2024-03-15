package Services;

import Entities.IBank;
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
    List<String> GetAllBankNames();
    IBank GetBankByName(@NonNull String name);
    String InterbankTransfer(@NonNull String sourceBankName,
                             @NonNull String sourceId,
                             @NonNull String targetBankName,
                             @NonNull String targetId,
                             float amount);
}
