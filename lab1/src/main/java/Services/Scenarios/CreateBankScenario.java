package Services.Scenarios;

import Services.Context;
import lombok.NonNull;

public class CreateBankScenario implements IScenario
{
    public static void Execute(@NonNull Context context)
    {
        String name = context.get_textIO().newStringInputReader()
                .read("Enter bank's name: ");

        while (context.get_centralBank().GetBankByName(name) != null)
            name = context.get_textIO().newStringInputReader()
                    .read("Bank already exists. Try again.\nEnter bank's name: ");

        float commission = context.get_textIO().newFloatInputReader()
                .withMinVal(0f)
                .read("Enter commission: ");

        float interestOnBalanceDebit = context.get_textIO().newFloatInputReader()
                .withMinVal(0f)
                .withMaxVal(100f)
                .read("Enter interest on the balance for debit accounts: ");

        float interestOnBalanceLowDeposit = context.get_textIO().newFloatInputReader()
                .withMinVal(0f)
                .withMaxVal(100f)
                .read("Enter interest on the balance for deposit accounts with initial amount lower than 100k: ");

        float interestOnBalanceHighDeposit = context.get_textIO().newFloatInputReader()
                .withMinVal(0f)
                .withMaxVal(100f)
                .read("Enter interest on the balance for deposit accounts with initial amount higher than 100k: ");

        float doubtSum = context.get_textIO().newFloatInputReader()
                .withMinVal(0f)
                .withMaxVal(100f)
                .read("Enter sum for doubtful clients: ");

        float creditLimit = context.get_textIO().newFloatInputReader()
                .withMinVal(0f)
                .read("Enter credit limit: ");

        float depositBorder = context.get_textIO().newFloatInputReader()
                .withMinVal(0f)
                .read("Enter deposit border: ");

        int depositPeriod = context.get_textIO().newIntInputReader()
                .withMinVal(1)
                .read("Enter deposit period ");


        context.get_centralBank().CreateBank(name, commission, doubtSum, creditLimit,
                interestOnBalanceDebit, interestOnBalanceLowDeposit, interestOnBalanceHighDeposit,
                depositBorder, depositPeriod);

        context.get_textIO().getTextTerminal().printf("Bank %s successfully created.\n", name);

        context.set_currentBank(context.get_centralBank().GetBankByName(name));

        ClientEnterScenario.Execute(context);
    }
}
