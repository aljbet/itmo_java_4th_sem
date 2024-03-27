import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Presentation {
    private static final TextIO _textIO = TextIoFactory.getTextIO();
    private static final ICatService catService = new CatService();

    public static void start() {
        _textIO.getTextTerminal().println("Welcome to the Cat Service!");
        String action = _textIO.newStringInputReader()
                .withPossibleValues("add new cat", "add new owner",
                        "get all cats", "get all owners",
                        "get cat by name", "get cats by owner",
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
                for (String cat : catService.getAllCats())
                {
                    _textIO.getTextTerminal().println(cat);
                }
                break;
            case "get all owners":
                for (String owner : catService.getAllOwners())
                {
                    _textIO.getTextTerminal().println(owner);
                }
                break;
            case "get cat by name":
                getCatByName();
                break;
            case "get cats by owner":
                getCatsByOwner();
                break;
            case "set friendship":
                setFriendship();
                break;
            case "exit":
                System.exit(0);
        }
        start();
    }

    private static void addNewCat() {
        String name = _textIO.newStringInputReader().read("Enter cat's name: ");
        if (catService.getCatByName(name) != null)
        {
            _textIO.getTextTerminal().println("That cat is already in database. Try again.");
            return;
        }
        String dateOfBirth = _textIO.newStringInputReader().read("Enter cat's date of birth (DD.MM.YYYY): ");
        if (!validateDate(dateOfBirth))
        {
            _textIO.getTextTerminal().println("Invalid date. Try again.");
            return;
        }
        String breed = _textIO.newStringInputReader().read("Enter cat's breed: ");
        String color = _textIO.newStringInputReader()
                .withPossibleValues(catService.getPossibleColors())
                .read("Enter cat's color: ");
        String ownerName = _textIO.newStringInputReader().read("Enter cat's owner's name: ");
        if (catService.getOwnerByName(name) == null)
        {
            _textIO.getTextTerminal().println("That cat is already in database. Try again.");
            return;
        }
        catService.addNewCat(name, dateOfBirth, breed, color, ownerName);
    }

    private static void addNewOwner() {
        String name = _textIO.newStringInputReader().read("Enter owner's name: ");
        if (catService.getOwnerByName(name) != null)
        {
            _textIO.getTextTerminal().println("That owner is already in database. Try again.");
            return;
        }
        String dateOfBirth = _textIO.newStringInputReader().read("Enter owner's date of birth (DD.MM.YYYY): ");
        if (!validateDate(dateOfBirth))
        {
            _textIO.getTextTerminal().println("Invalid date. Try again.");
            return;
        }
        catService.addNewOwner(name, dateOfBirth);
    }

    private static void getCatByName() {
        String name = _textIO.newStringInputReader().read("Enter cat's name: ");
        String res = catService.getCatByName(name);
        _textIO.getTextTerminal().println(Objects.requireNonNullElse(res, "That cat is not in database."));
    }

    private static void getCatsByOwner() {
        String ownerName = _textIO.newStringInputReader().read("Enter owner's name: ");
        if (catService.getOwnerByName(ownerName) == null)
        {
            _textIO.getTextTerminal().println("That owner is not in database.");
            return;
        }
        List<String> cats = catService.getCatsByOwner(ownerName);
        for (String cat : cats) {
            _textIO.getTextTerminal().println(cat);
        }
    }


    private static void setFriendship() {
        String firstCatName = _textIO.newStringInputReader().read("Enter first cat's name: ");
        if (catService.getCatByName(firstCatName) == null)
        {
            _textIO.getTextTerminal().println("First cat is not in database.");
            return;
        }
        String secondCatName = _textIO.newStringInputReader().read("Enter second cat's name: ");
        if (catService.getCatByName(secondCatName) == null)
        {
            _textIO.getTextTerminal().println("Second cat is not in database.");
            return;
        }
        catService.setFriendship(firstCatName, secondCatName);
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
