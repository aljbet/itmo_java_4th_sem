package Services;

import Services.Scenarios.BeginScenario;

public class Program
{
    private static final Context _context = new Context();

    public static void main(String[] args)
    {
        BeginScenario.Execute(_context);
        System.exit(0);
    }
}