package Services.Scenarios;

import Services.Context;
import lombok.NonNull;

public class MainActionChoiceScenario implements IScenario
{
    public static void Execute(@NonNull Context context)
    {
        String action = context.get_textIO().newStringInputReader()
                .withPossibleValues("create new account", "withdraw", "deposit", "in-bank transfer",
                        "interbank transfer", "set address", "set passport", "choose another bank", "exit")
                .read("Choose action: ");

        if (action.equals("create new account"))
        {
            CreateNewAccountScenario.Execute(context);
        }
        else if (action.equals("withdraw"))
        {
//            WithdrawScenario.Execute(context);
        }
        else if (action.equals("deposit"))
        {
//            DepositScenario.Execute(context);
        }
        else if (action.equals("in-bank transfer"))
        {
//            InBankTransferScenario.Execute(context);
        }
        else if (action.equals("interbank transfer"))
        {
//            InterbankTransferScenario.Execute(context);
        }
        else if (action.equals("set address"))
        {
            context.get_currentClient().SetAddress(context.get_textIO().newStringInputReader().read("Enter address: "));
        }
        else if (action.equals("set passport"))
        {
            context.get_currentClient().SetPassport(context.get_textIO().newStringInputReader().read("Enter passport: "));
        }
        else if (action.equals("choose another bank"))
        {
            ChooseBankScenario.Execute(context);
        }
        else if (context.get_textIO().newBooleanInputReader().read("Exit?"))
            System.exit(0);
    }
}
