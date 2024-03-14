package Services.Scenarios;

import Services.Context;
import lombok.NonNull;

public class CreateNewAccountScenario implements IScenario
{
    public static void Execute(@NonNull Context context)
    {
        String type = context.get_textIO().newStringInputReader()
                .withPossibleValues("credit", "debit", "deposit")
                .read("Choose type of account: ");

        String id = context.get_textIO().newStringInputReader()
                .read("Enter id: ");

        if (context.get_currentBank().GetAccountById(id) != null)
        {
            context.get_textIO().getTextTerminal().printf("Account already exists. Please try again.\n");
            CreateNewAccountScenario.Execute(context);
        }
        else
        {
            switch (type)
            {
                case "credit" -> context.get_currentBank().CreateCreditAccount(id, context.get_currentClient());
                case "debit" -> context.get_currentBank().CreateDebitAccount(id, context.get_currentClient());
                case "deposit" -> context.get_currentBank().CreateDepositAccount(id, context.get_currentClient());
            }

            context.get_textIO().getTextTerminal().printf("Account created successfully.\n");
        }
    }
}
