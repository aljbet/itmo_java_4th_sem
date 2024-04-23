import app.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

public class ServiceRepositoryTest {

    private final CatRepository catRepository = Mockito.mock(CatRepository.class);
    private final OwnerRepository ownerRepository = Mockito.mock(OwnerRepository.class);

    @ParameterizedTest
    @CsvSource({"a,b,c,d,e"})
    public void addNewCatTest(String catName, String dateOfBirth, String breed, String color, String ownerName) {
        CatService catService = new CatService(catRepository, ownerRepository);
        CatDto cat = new CatDto(catName, dateOfBirth, breed, color, ownerName);

        catService.addNewCat(cat);

        Mockito.verify(catRepository).save(cat.getCat(ownerRepository));
    }

    @ParameterizedTest
    @CsvSource({"a,b"})
    public void addNewOwnerTest(String ownerName, String dateOfBirth) {
        CatService catService = new CatService(catRepository, ownerRepository);
        OwnerDto owner = new OwnerDto(ownerName, dateOfBirth);

        catService.addNewOwner(owner);

        Mockito.verify(ownerRepository).save(owner.getOwner());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a"})
    public void deleteCatTest(String name) {
        CatService catService = new CatService(catRepository, ownerRepository);
        Cat cat = new Cat();

        Mockito.when(catRepository.findCatByName(name)).thenReturn(cat);
        catService.deleteCat(name);

        Mockito.verify(catRepository).findCatByName(name);
        Mockito.verify(catRepository).delete(cat);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a"})
    public void deleteOwnerTest(String name) {
        CatService catService = new CatService(catRepository, ownerRepository);
        Owner owner = new Owner();

        Mockito.when(ownerRepository.findOwnerByName(name)).thenReturn(owner);
        catService.deleteOwner(name);

        Mockito.verify(ownerRepository).findOwnerByName(name);
        Mockito.verify(ownerRepository).delete(owner);
    }

    @Test
    public void getAllCatsTest() {
        CatService catService = new CatService(catRepository, ownerRepository);
        catService.getAllCats();
        Mockito.verify(catRepository).findAll();
    }

    @Test
    public void getAllOwnersTest() {
        CatService catService = new CatService(catRepository, ownerRepository);
        catService.getAllOwners();
        Mockito.verify(ownerRepository).findAll();
    }

    @Test
    public void getCatByNameTest() {
        CatService catService = new CatService(catRepository, ownerRepository);
        catService.getCatByName("a");
        Mockito.verify(catRepository).findCatByName("a");
    }

    @Test
    public void getOwnerByNameTest() {
        CatService catService = new CatService(catRepository, ownerRepository);
        catService.getOwnerByName("a");
        Mockito.verify(ownerRepository).findOwnerByName("a");
    }

    @ParameterizedTest
    @CsvSource({"a,b"})
    public void setFriendshipTest(String firstCatName, String secondCatName) {
        CatService catService = new CatService(catRepository, ownerRepository);
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();

        Mockito.when(catRepository.findCatByName(firstCatName)).thenReturn(cat1);
        Mockito.when(catRepository.findCatByName(secondCatName)).thenReturn(cat2);

        catService.setFriendship(firstCatName, secondCatName);

        Mockito.verify(catRepository).findCatByName(firstCatName);
        Mockito.verify(catRepository).findCatByName(secondCatName);
    }
}
