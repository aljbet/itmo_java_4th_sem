package Services.Scenarios;

import Services.Context;
import lombok.NonNull;

public class BeginScenario implements IScenario
{
    public static void Execute(@NonNull Context context)
    {
        String action = context.get_textIO().newStringInputReader()
                .withPossibleValues("create bank", "choose bank", "exit")
                .read("Choose action: ");

        if (action.equals("create bank"))
            CreateBankScenario.Execute(context);
        else if (action.equals("choose bank"))
            ChooseBankScenario.Execute(context);
        else if (context.get_textIO().newBooleanInputReader().read("Exit?"))
            System.exit(0);
    }
}
