import java.util.List;
import java.util.Vector;

public class CatService implements ICatService
{
    private CatDao catDao = new CatDao();
    private OwnerDao ownerDao = new OwnerDao();
    @Override
    public void addNewCat(String catName, String dateOfBirth, String breed, String color, String ownerName) {
    }

    @Override
    public void addNewOwner(String name, String dateOfBirth) {

    }

    @Override
    public List<String> getAllCats() {
        Vector<String> res = new Vector<>();
        for (Cat cat : catDao.getAll()) res.add(cat.toString());
        return res;
    }

    @Override
    public List<String> getAllOwners() {
        Vector<String> res = new Vector<>();
        for (Owner owner : ownerDao.getAll()) res.add(owner.toString());
        return res;
    }
    @Override
    public String getCatByName(String name) {
        return null;
    }

    @Override
    public List<String> getCatsByOwner(String owner) {
        return null;
    }

    @Override
    public List<String> getCatsFriends(String name) {
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
