import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

public class CatServiceTest {

    private final CatDao catDao = Mockito.mock(CatDao.class);
    private final OwnerDao ownerDao = Mockito.mock(OwnerDao.class);

    @ParameterizedTest
    @CsvSource({"a,b,c,d,e"})
    public void addNewCatTest(String catName, String dateOfBirth, String breed, String color, String ownerName) {
        CatService catService = new CatService(catDao, ownerDao);
        Owner owner = new Owner();
        Cat cat = new Cat();
        cat.setName(catName);
        cat.setDateOfBirth(dateOfBirth);
        cat.setBreed(breed);
        cat.setColor(color);
        cat.setOwner(owner);

        catService.addNewCat(catName, dateOfBirth, breed, color, ownerName);
        Mockito.when(ownerDao.getByName(ownerName)).thenReturn(owner);

        Mockito.verify(ownerDao).getByName(ownerName);
    }

    @ParameterizedTest
    @CsvSource({"a,b"})
    public void addNewOwnerTest(String ownerName, String dateOfBirth) {
        CatService catService = new CatService(catDao, ownerDao);
        Owner owner = new Owner();
        owner.setName(ownerName);
        owner.setDateOfBirth(dateOfBirth);

        catService.addNewOwner(ownerName, dateOfBirth);

        Mockito.verify(ownerDao).create(owner);
    }

    @Test
    public void getAllCatsTest() {
        CatService catService = new CatService(catDao, ownerDao);
        catService.getAllCats();
        Mockito.verify(catDao).getAll();
    }

    @Test
    public void getAllOwnersTest() {
        CatService catService = new CatService(catDao, ownerDao);
        catService.getAllOwners();
        Mockito.verify(ownerDao).getAll();
    }

    @Test
    public void getCatByNameTest() {
        CatService catService = new CatService(catDao, ownerDao);
        catService.getCatByName("a");
        Mockito.verify(catDao).getByName("a");
    }

    @Test
    public void getOwnerByNameTest() {
        CatService catService = new CatService(catDao, ownerDao);
        catService.getOwnerByName("a");
        Mockito.verify(ownerDao).getByName("a");
    }

    @Test
    public void catExistsTest() {
        CatService catService = new CatService(catDao, ownerDao);
        catService.catExists("a");
        Mockito.verify(catDao).getByName("a");
    }

    @Test
    public void ownerByExistsTest() {
        CatService catService = new CatService(catDao, ownerDao);
        catService.ownerExists("a");
        Mockito.verify(ownerDao).getByName("a");
    }

    @ParameterizedTest
    @CsvSource({"a,b"})
    public void setFriendshipTest(String firstCatName, String secondCatName) {
        CatService catService = new CatService(catDao, ownerDao);
        catService.setFriendship(firstCatName, secondCatName);
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();

        Mockito.when(catDao.getByName(firstCatName)).thenReturn(cat1);
        Mockito.when(catDao.getByName(secondCatName)).thenReturn(cat2);

        Mockito.verify(catDao).getByName(firstCatName);
        Mockito.verify(catDao).getByName(secondCatName);
    }
}
