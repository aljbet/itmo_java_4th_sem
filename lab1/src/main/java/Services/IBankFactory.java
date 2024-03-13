package Services;

import Entities.IBank;

import java.util.Vector;

public interface IBankFactory
{
    void AddBank(IBank bank);

    Vector<IBank> GetAllBanks();
}
