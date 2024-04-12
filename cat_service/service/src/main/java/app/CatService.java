package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Vector;

@Component
public class CatService implements ICatService
{
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;
    @Autowired
    public CatService(CatRepository catRepository, OwnerRepository ownerRepository) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void addNewCat(CatDto cat) {
        catRepository.save(cat.getCat(ownerRepository));
    }

    @Override
    public void addNewOwner(OwnerDto owner) {
        ownerRepository.save(owner.getOwner());
    }

    @Override
    public void deleteCat(String name) {
        Cat cat = catRepository.findCatByName(name);
        if (cat == null) return;
        for (Cat friend : cat.getFriends()) {
            friend.getFriends().remove(cat);
        }
        catRepository.delete(cat);
    }

    @Override
    public void deleteOwner(String name) {
        Owner owner = ownerRepository.findOwnerByName(name);
        if (owner == null) return;
        for (Cat cat : owner.getCats()) {
            deleteCat(cat.getName());
        }
        ownerRepository.delete(owner);
    }

    @Override
    public List<CatDto> getAllCats() {
        Vector<CatDto> res = new Vector<>();
        for (Cat cat : catRepository.findAll()) res.add(new CatDto(cat));
        return res;
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        Vector<OwnerDto> res = new Vector<>();
        for (Owner owner : ownerRepository.findAll()) res.add(new OwnerDto(owner));
        return res;
    }

    @Override
    public CatDto getCatByName(String name) {
        Cat cat = catRepository.findCatByName(name);
        if (cat == null) return null;
        return new CatDto(cat);
    }

    @Override
    public OwnerDto getOwnerByName(String name) {
        Owner owner = ownerRepository.findOwnerByName(name);
        if (owner == null) return null;
        return new OwnerDto(owner);
    }

    @Override
    public void setFriendship(String firstCatName, String secondCatName) {
        Cat firstCat = catRepository.findCatByName(firstCatName);
        Cat secondCat = catRepository.findCatByName(secondCatName);
        if (firstCat == null || secondCat == null) return;
        if (!firstCat.getFriends().contains(secondCat)) firstCat.getFriends().add(secondCat);
        if (!secondCat.getFriends().contains(firstCat)) secondCat.getFriends().add(firstCat);
        catRepository.save(firstCat);
        catRepository.save(secondCat);
    }
}
