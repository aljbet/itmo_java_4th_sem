import java.util.List;

public class CatService implements ICatService
{
    @Override
    public void addNewCat(String catName, String dateOfBirth, String breed, String color, String ownerName) {

    }

    @Override
    public void addNewOwner(String name, String dateOfBirth) {

    }

    @Override
    public List<String> getAllCats() {
        return null;
    }

    @Override
    public List<String> getAllOwners() {
        return null;
    }

    @Override
    public List<String> getCatsByOwner(String owner) {
        return null;
    }

    @Override
    public String getCatByName(String name) {
        return null;
    }

    @Override
    public String getOwnerByName(String name) {
        return null;
    }

    @Override
    public List<String> getPossibleColors() {
        return null;
    }

    @Override
    public void setFriendship(String firstCatName, String secondCatName) {

    }
}
