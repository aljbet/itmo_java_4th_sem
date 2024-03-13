package Entities;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Bank implements IBank
{
    private final String _name;
    private final float _commission;
    private final float _interestOnBalanceDebit;
    private final float _interestOnBalanceLowDeposit;
    private final float _interestOnBalanceHighDeposit;

    public Bank(
            @NonNull String name,
            float commission,
            float iobDebit,
            float iobLowDeposit,
            float iobHighDeposit)
    {
        _name = name;
        _commission = commission;
        _interestOnBalanceDebit = iobDebit;
        _interestOnBalanceLowDeposit = iobLowDeposit;
        _interestOnBalanceHighDeposit = iobHighDeposit;
    }

    @Override
    public String GetName()
    {
        return _name;
    }
}
