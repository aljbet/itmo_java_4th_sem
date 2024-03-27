import java.util.List;

public interface ICatService {
    void addNewCat(String catName, String dateOfBirth, String breed, String color, String ownerName);
    void addNewOwner(String name, String dateOfBirth);
    List<String> getAllCats();
    List<String> getAllOwners();
    List<String> getCatsByOwner(String owner);
    String getCatByName(String name);
    String getOwnerByName(String name);
    List<String> getPossibleColors();
    void setFriendship(String firstCatName, String secondCatName);
}
