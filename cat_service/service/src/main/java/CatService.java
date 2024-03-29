import java.util.List;
import java.util.Vector;

public class CatService implements ICatService {
    private final CatDao catDao;
    private final OwnerDao ownerDao;
    private final List<String> possibleColors;

    public CatService() {
        catDao = new CatDao();
        ownerDao = new OwnerDao();
        possibleColors = new Vector<>();

        possibleColors.add("black");
        possibleColors.add("grey");
        possibleColors.add("white");
        possibleColors.add("ginger");
    }

    public CatService(CatDao catDao, OwnerDao ownerDao) {
        this.catDao = catDao;
        this.ownerDao = ownerDao;
        possibleColors = new Vector<>();

        possibleColors.add("black");
        possibleColors.add("grey");
        possibleColors.add("white");
        possibleColors.add("ginger");
    }

    @Override
    public void addNewCat(String catName, String dateOfBirth, String breed, String color, String ownerName) {
        Cat cat = new Cat();
        cat.setName(catName);
        cat.setDateOfBirth(dateOfBirth);
        cat.setBreed(breed);
        cat.setColor(color);
        Owner owner = ownerDao.getByName(ownerName);
        cat.setOwner(owner);
        catDao.create(cat);
    }

    @Override
    public void addNewOwner(String name, String dateOfBirth) {
        Owner owner = new Owner();
        owner.setName(name);
        owner.setDateOfBirth(dateOfBirth);
        ownerDao.create(owner);
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
        Cat cat = catDao.getByName(name);
        if (cat == null) return null;
        return cat.toString();
    }

    @Override
    public String getOwnerByName(String name) {
        Owner owner = ownerDao.getByName(name);
        if (owner == null) return null;
        return owner.toString();
    }

    @Override
    public boolean catExists(String name) {
        return catDao.getByName(name) != null;
    }

    @Override
    public boolean ownerExists(String name) {
        return ownerDao.getByName(name) != null;
    }

    @Override
    public List<String> getPossibleColors() {
        return possibleColors;
    }

    @Override
    public void setFriendship(String firstCatName, String secondCatName) {
        Cat firstCat = catDao.getByName(firstCatName);
        Cat secondCat = catDao.getByName(secondCatName);
        if (firstCat == null || secondCat == null) return;
        firstCat.getFriends().add(secondCat);
        secondCat.getFriends().add(firstCat);
        catDao.update(firstCat);
        catDao.update(secondCat);
    }
}
