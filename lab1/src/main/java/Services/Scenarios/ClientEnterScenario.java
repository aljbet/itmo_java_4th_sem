package Services.Scenarios;

import Entities.IClient;
import Services.Context;
import lombok.NonNull;

public class ClientEnterScenario implements IScenario
{
    public static void Execute(@NonNull Context context)
    {
        String action = context.get_textIO().newStringInputReader()
                .withPossibleValues("log in", "sign up", "exit")
                .read("Choose action: ");

        String name = context.get_textIO().newStringInputReader()
                .read("Enter your full name: ");

        IClient client = context.get_currentBank().GetClientByName(name);

        if (action.equals("log in"))
        {
            if (client != null)
            {
                context.get_textIO().getTextTerminal().printf("Successful log in.\n");
                context.set_currentClient(client);

                MainActionChoiceScenario.Execute(context);
            }
            else
            {
                context.get_textIO().getTextTerminal().printf("We couldn't find you. Please try again.\n");
                ClientEnterScenario.Execute(context);
            }
        }
        else if (action.equals("sign up"))
        {
            if (client == null)
            {
                context.get_textIO().getTextTerminal().print("Enter your address: ");
                String address = context.get_textIO().getTextTerminal().read(false);
                context.get_textIO().getTextTerminal().print("Enter your passport: ");
                String passport = context.get_textIO().getTextTerminal().read(false);
                context.get_currentBank().CreateClient(name, address, passport);
                context.get_textIO().getTextTerminal().printf("Client %s successfully created.\n", name);

                client = context.get_currentBank().GetClientByName(name);
                context.set_currentClient(client);

                MainActionChoiceScenario.Execute(context);
            }
            else
            {
                context.get_textIO().getTextTerminal().printf("Client already exists. Please try again.\n");
                ClientEnterScenario.Execute(context);
            }
        }
        else if (context.get_textIO().newBooleanInputReader().read("Exit?"))
            System.exit(0);
    }
}
