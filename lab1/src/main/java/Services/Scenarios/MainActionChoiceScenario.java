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

        switch (action)
        {
            case "create new account" -> CreateNewAccountScenario.Execute(context);
            case "withdraw" -> WithdrawScenario.Execute(context);
            case "deposit" -> DepositScenario.Execute(context);
            case "in-bank transfer" -> InBankTransferScenario.Execute(context);
            case "interbank transfer" -> InterbankTransferScenario.Execute(context);
            case "set address" ->
                    context.get_currentClient().SetAddress(
                            context.get_textIO().newStringInputReader()
                                    .read("Enter address: "));
            case "set passport" ->
                    context.get_currentClient().SetPassport(
                            context.get_textIO().newStringInputReader()
                                    .read("Enter passport: "));
            case "choose another bank" -> ChooseBankScenario.Execute(context);
            default ->
            {
                if (context.get_textIO().newBooleanInputReader().read("Exit?"))
                    System.exit(0);
            }
        }
    }
}
