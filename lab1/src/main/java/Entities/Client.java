package Entities;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class Client implements IClient
{
    private String _fullName;
    private String _address;
    private String _passport;

    @Override
    public String GetFullName()
    {
        return _fullName;
    }

    @Override
    public void SetAddress(@NonNull String address)
    {
        _address = address;
    }

    @Override
    public void SetPassport(@NonNull String passport)
    {
        _passport = passport;
    }
}
