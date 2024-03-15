package Entities;

import lombok.NonNull;

public interface IClient
{
    String GetFullName();
    String GetAddress();
    String GetPassport();
    void SetAddress(@NonNull String address);
    void SetPassport(@NonNull String passport);
    void ReceiveMessage(@NonNull String message);
}
