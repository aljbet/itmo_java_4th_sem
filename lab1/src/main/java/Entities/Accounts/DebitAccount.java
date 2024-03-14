package Entities.Accounts;

import Entities.IClient;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class DebitAccount implements IAccount
{
    private String _id;
    private float _balance = 0;
    private DebitAccount _previousState = null;
    private IClient _owner;

    public DebitAccount(@NonNull String id, @NonNull IClient owner)
    {
        _id = id;
        _owner = owner;
    }

    @Override
    public void CancelTransaction()
    {
        this._balance = _previousState.get_balance();
        this._previousState = _previousState.get_previousState();
    }

    @Override
    public String GetId()
    {
        return _id;
    }

    @Override
    public IClient GetOwner()
    {
        return _owner;
    }
}
