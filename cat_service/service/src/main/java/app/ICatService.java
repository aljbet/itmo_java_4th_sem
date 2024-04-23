package app;

import java.util.List;

public interface ICatService {
    void addNewCat(CatDto cat);
    void addNewOwner(OwnerDto owner);
    void deleteCat(String name);
    void deleteOwner(String name);
    List<CatDto> getAllCats();
    List<OwnerDto> getAllOwners();
    CatDto getCatByName(String name);
    OwnerDto getOwnerByName(String name);
    void setFriendship(String firstCatName, String secondCatName);
}
