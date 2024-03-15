package Services.Scenarios;

import Services.Context;
import lombok.NonNull;

public class InterbankTransferScenario implements IScenario
{
    public static void Execute(@NonNull Context context)
    {
        String targetBank = context.get_textIO().newStringInputReader()
                .withPossibleValues(context.get_centralBank().GetAllBankNames())
                .read("Choose bank: ");

        String sourceId = context.get_textIO().newStringInputReader()
                .read("Enter source account id: ");

        String targetId = context.get_textIO().newStringInputReader()
                .read("Enter target account id: ");

        float amount = context.get_textIO().newFloatInputReader()
                .read("Enter amount: ");

        context.get_textIO().getTextTerminal()
                .printf(context.get_centralBank()
                        .InterbankTransfer(context.get_currentBank().GetName(),
                                sourceId, targetBank, targetId, amount));
    }
}
