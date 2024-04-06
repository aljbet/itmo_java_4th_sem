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
    public void addNewCat(CatDto cat) {
        catDao.create(cat.getCat(ownerDao));
    }

    @Override
    public void addNewOwner(OwnerDto owner) {
        ownerDao.create(owner.getOwner());
    }

    @Override
    public List<CatDto> getAllCats() {
        Vector<CatDto> res = new Vector<>();
        for (Cat cat : catDao.getAll()) res.add(new CatDto(cat));
        return res;
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        Vector<OwnerDto> res = new Vector<>();
        for (Owner owner : ownerDao.getAll()) res.add(new OwnerDto(owner));
        return res;
    }

    @Override
    public CatDto getCatByName(String name) {
        Cat cat = catDao.getByName(name);
        if (cat == null) return null;
        return new CatDto(cat);
    }

    @Override
    public OwnerDto getOwnerByName(String name) {
        Owner owner = ownerDao.getByName(name);
        if (owner == null) return null;
        return new OwnerDto(owner);
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
