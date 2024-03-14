package Services.Scenarios;

import Services.Context;
import lombok.NonNull;

public class ChooseBankScenario implements IScenario
{
    public static void Execute(@NonNull Context context)
    {
        String bankName = context.get_textIO().newStringInputReader()
                .withPossibleValues(context.get_centralBank().GetAllBankNames())
                .read("Choose bank: ");

        context.set_currentBank(context.get_centralBank().GetBankByName(bankName));

        ClientEnterScenario.Execute(context);
    }
}
