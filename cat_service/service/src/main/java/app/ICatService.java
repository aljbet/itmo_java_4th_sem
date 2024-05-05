package app;

import java.util.List;

public interface ICatService {
    void addNewCat(CatDto cat);
    void deleteCat(String name);
    void deleteOwner(String name);
    List<CatDto> getAllCats();
    List<CatDto> getAllCatsByOwner(String ownerName);
    List<CatDto> getAllCatsByBreed(String breed);
    List<CatDto> getAllCatsByColor(String color);
    List<CatDto> getAllCatsByBreedAndOwner(String breed, String ownerName);
    List<CatDto> getAllCatsByColorAndOwner(String color, String ownerName);
    List<OwnerDto> getAllOwners();
    CatDto getCatByName(String name);
    OwnerDto getOwnerByName(String name);
    void setFriendship(String firstCatName, String secondCatName);
}
