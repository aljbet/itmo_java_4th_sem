package Services.Scenarios;

import Services.Context;
import lombok.NonNull;

public class DepositScenario implements IScenario
{
    public static void Execute(@NonNull Context context)
    {
        String id = context.get_textIO().newStringInputReader()
                .read("Enter id: ");

        if (context.get_currentBank().GetAccountById(id) == null)
        {
            context.get_textIO().getTextTerminal().printf("We couldn't find your account. Please try again.\n");
        }
        else
        {
            float amount = context.get_textIO().newFloatInputReader()
                    .withMinVal(0.01f)
                    .read("Enter amount: ");

            context.get_textIO().getTextTerminal().printf(context.get_currentBank().Deposit(id, amount));
        }
        MainActionChoiceScenario.Execute(context);
    }
}
