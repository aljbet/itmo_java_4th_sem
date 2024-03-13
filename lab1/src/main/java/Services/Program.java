package Services;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class Program
{
    static TextIO _textIO = TextIoFactory.getTextIO();
    static ICentralBank _centralBank = new CentralBank();

    public static void main(String[] args)
    {
        String action = _textIO.newStringInputReader()
                .withPossibleValues("create bank", "choose bank", "exit")
                .read("Choose role");

//        _textIO.getTextTerminal().printf("\nYour role is %s.\n", role);
        if (action.equals("create bank"))
            CreateBank();
        else if (action.equals("choose bank"))
            ChooseBank();
        else
            System.exit(0);

        if (_textIO.newBooleanInputReader().read("Exit?"))
            System.exit(0);
        System.exit(0);
    }

    private static void CreateBank()
    {
        String name = _textIO.newStringInputReader()
                .read("Enter bank's name: ");

        while (_centralBank.GetBank(name) != null)
            name = _textIO.newStringInputReader()
                    .read("Bank already exists. Try again.\nEnter bank's name: ");

        float commission = _textIO.newFloatInputReader()
                .withMinVal(0f)
                .read("Enter commission: ");

        float interestOnBalanceDebit = _textIO.newFloatInputReader()
                .withMinVal(0f)
                .withMaxVal(100f)
                .read("Enter interest on the balance for debit accounts: ");

        float interestOnBalanceLowDeposit = _textIO.newFloatInputReader()
                .withMinVal(0f)
                .withMaxVal(100f)
                .read("Enter interest on the balance for deposit accounts with initial amount lower than 100k: ");

        float interestOnBalanceHighDeposit = _textIO.newFloatInputReader()
                .withMinVal(0f)
                .withMaxVal(100f)
                .read("Enter interest on the balance for deposit accounts with initial amount higher than 100k: ");

        _centralBank.CreateBank(name, commission, interestOnBalanceDebit, interestOnBalanceLowDeposit, interestOnBalanceHighDeposit);
    }

    private static void ChooseBank()
    {
        String bankName = _textIO.newStringInputReader()
                .withPossibleValues(_centralBank.GetAllBankNames())
                .read("Choose bank: ");

        _textIO.getTextTerminal().printf("\nYour bank is %s.\n", bankName);
        if (_textIO.newBooleanInputReader().read("Exit?"))
            System.exit(0);
    }
}