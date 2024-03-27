import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Presentation {
    private static final TextIO _textIO = TextIoFactory.getTextIO();

    public static void start() {
        _textIO.getTextTerminal().println("Welcome to the Cat Service!");
        String action = _textIO.newStringInputReader()
                .withPossibleValues("add new cat", "add new owner",
                        "get all cats", "get all owners",
                        "get cat by name", "get owner by name",
                        "set friendship", "exit")
                .read("Choose action:");

        switch (action)
        {
            case "add new cat":
                addNewCat();
                break;
            case "add new owner":
                addNewOwner();
                break;
            case "get all cats":
                CatService.getAllCats();
                break;
            case "get all owners":
                CatService.getAllOwners();
                break;
            case "get cat by name":
                getCatByName();
                break;
            case "get owner by name":
                getOwnerByName();
                break;
            case "set friendship":
                CatService.setFriendship();
                break;
            case "exit":
                System.exit(0);
        }
        start();
    }

    private static void addNewCat() {
        String name = _textIO.newStringInputReader().read("Enter cat's name: ");
        while (CatService.getCatByName(name) != null)
        {
            _textIO.getTextTerminal().println("That cat is already in database. Try again.");
            name = _textIO.newStringInputReader().read("Enter cat's name: ");
        }
        String dateOfBirth = _textIO.newStringInputReader().read("Enter cat's date of birth (DD.MM.YYYY): ");
        while (!validateDate(dateOfBirth))
        {
            _textIO.getTextTerminal().println("Invalid date. Try again.");
            dateOfBirth = _textIO.newStringInputReader().read("Enter cat's date of birth (DD.MM.YYYY): ");
        }
        String breed = _textIO.newStringInputReader().read("Enter cat's breed: ");
        String color = _textIO.newStringInputReader()
                .withPossibleValues("white", "black", "grey")
                .read("Enter cat's color: ");
        String ownerName = _textIO.newStringInputReader().read("Enter cat's owner's name: ");
        CatService.addNewCat(name, dateOfBirth, breed, color, ownerName);
    }

    private static void addNewOwner() {
        String name = _textIO.newStringInputReader().read("Enter owner's name: ");
        while (CatService.getOwnerByName(name) != null)
        {
            _textIO.getTextTerminal().println("That owner is already in database. Try again.");
            name = _textIO.newStringInputReader().read("Enter owner's name: ");
        }
        String dateOfBirth = _textIO.newStringInputReader().read("Enter owner's date of birth (DD.MM.YYYY): ");
        while (!validateDate(dateOfBirth))
        {
            _textIO.getTextTerminal().println("Invalid date. Try again.");
            dateOfBirth = _textIO.newStringInputReader().read("Enter owner's date of birth (DD.MM.YYYY): ");
        }
    }

    private static void getCatByName() {
        String name = _textIO.newStringInputReader().read("Enter cat's name: ");
        CatService.getCatByName(name);
    }

    private static void getOwnerByName() {
        String name = _textIO.newStringInputReader().read("Enter owner's name: ");
        CatService.getOwnerByName(name);
    }

    private static boolean validateDate(String dateOfBirth) {
        Pattern pattern = Pattern.compile("[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}");
        Matcher matcher = pattern.matcher(dateOfBirth);
        if (!matcher.find()) return false;
        int day = Integer.parseInt(dateOfBirth.substring(0, 2));
        int month = Integer.parseInt(dateOfBirth.substring(3, 5));
        int year = Integer.parseInt(dateOfBirth.substring(6, 10));
        return day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 1900 && year <= 2024;
    }
}
