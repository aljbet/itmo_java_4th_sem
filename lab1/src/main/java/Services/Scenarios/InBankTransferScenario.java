package Services.Scenarios;

import Services.Context;
import lombok.NonNull;

public class InBankTransferScenario implements IScenario
{
    public static void Execute(@NonNull Context context)
    {
        String sourceId = context.get_textIO().newStringInputReader()
                .read("Enter source account id: ");

        String targetId = context.get_textIO().newStringInputReader()
                .read("Enter target account id: ");

        if (context.get_currentBank().GetAccountById(sourceId) == null)
        {
            context.get_textIO().getTextTerminal()
                    .printf("Failed. We couldn't find source account. Please try again.\n");
        }
        else if (context.get_currentBank().GetAccountById(targetId) == null)
        {
            context.get_textIO().getTextTerminal()
                    .printf("Failed. We couldn't find target account. Please try again.\n");
        }
        else
        {
            float amount = context.get_textIO().newFloatInputReader()
                    .withMinVal(0.01f)
                    .read("Enter amount: ");
            context.get_textIO().getTextTerminal()
                    .printf(context.get_currentBank().Transfer(sourceId, targetId, amount));
        }
        MainActionChoiceScenario.Execute(context);
    }
}
