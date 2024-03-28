import java.util.List;

public interface ICatService {
    void addNewCat(String catName, String dateOfBirth, String breed, String color, String ownerName);
    void addNewOwner(String name, String dateOfBirth);
    List<String> getAllCats();
    List<String> getAllOwners();
    String getCatByName(String name);
    List<String> getCatsByOwner(String owner);
    List<String> getCatsFriends(String name);
    String getOwnerByName(String name);
    List<String> getPossibleColors();
    void setFriendship(String firstCatName, String secondCatName);
}
