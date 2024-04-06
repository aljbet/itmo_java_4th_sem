import java.util.List;

public interface ICatService {
    void addNewCat(CatDto cat);
    void addNewOwner(OwnerDto owner);
    List<CatDto> getAllCats();
    List<OwnerDto> getAllOwners();
    CatDto getCatByName(String name);
    OwnerDto getOwnerByName(String name);
    boolean catExists(String name);
    boolean ownerExists(String name);
    List<String> getPossibleColors();
    void setFriendship(String firstCatName, String secondCatName);
}
