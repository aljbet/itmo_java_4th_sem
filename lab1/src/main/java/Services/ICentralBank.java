package Services;

import Entities.IBank;

import java.util.List;

public interface ICentralBank
{
    IBank GetBankByName(String name);

    List<String> GetAllBankNames();

    void CreateBank(String Name, float commission, float iobDebit, float iobLowDeposit, float iobHighDeposit, float doubtSum);
}
