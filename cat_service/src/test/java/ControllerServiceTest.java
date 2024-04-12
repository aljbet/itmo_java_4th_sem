import app.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

public class ControllerServiceTest {
    private final CatService catService = Mockito.mock(CatService.class);

    @ParameterizedTest
    @ValueSource(strings = {"01.01.2020"})
    public void addCatTest(String date) {
        CatController catController = new CatController(catService);
        CatDto cat = new CatDto("a", date, "c", "d", "e");

        catController.addCat(cat);

        Mockito.verify(catService).addNewCat(cat);
    }

    @ParameterizedTest
    @ValueSource(strings = {"asc", "01:01:2020", "01.01.2040"})
    public void addCatWithInvalidDateTest(String date) {
        CatController catController = new CatController(catService);
        CatDto cat = new CatDto("a", date, "c", "d", "e");

        catController.addCat(cat);

        Mockito.verify(catService, Mockito.never()).addNewCat(cat);
    }

    @ParameterizedTest
    @ValueSource(strings = {"01.01.2001"})
    public void addOwnerTest(String date) {
        CatController catController = new CatController(catService);
        OwnerDto owner = new OwnerDto("a", date);

        catController.addOwner(owner);

        Mockito.verify(catService).addNewOwner(owner);
    }

    @ParameterizedTest
    @ValueSource(strings = {"asc", "01:01:2020", "01.01.2040"})
    public void addOwnerWithInvalidDateTest(String date) {
        CatController catController = new CatController(catService);
        OwnerDto owner = new OwnerDto("a", date);

        catController.addOwner(owner);

        Mockito.verify(catService, Mockito.never()).addNewOwner(owner);
    }

    @ParameterizedTest
    @ValueSource(strings = {"name"})
    public void deleteCatTest(String name) {
        CatController catController = new CatController(catService);

        catController.deleteCat(name);

        Mockito.verify(catService).deleteCat(name);
    }

    @ParameterizedTest
    @ValueSource(strings = {"name"})
    public void deleteOwnerTest(String name) {
        CatController catController = new CatController(catService);

        catController.deleteOwner(name);

        Mockito.verify(catService).deleteOwner(name);
    }

    @Test
    public void getAllCatsTest() {
        CatController catController = new CatController(catService);

        catController.getAllCats();

        Mockito.verify(catService).getAllCats();
    }

    @Test
    public void getAllOwnersTest() {
        CatController catController = new CatController(catService);

        catController.getAllOwners();

        Mockito.verify(catService).getAllOwners();
    }

    @ParameterizedTest
    @ValueSource(strings = {"name"})
    public void getCatByNameTest(String name) {
        CatController catController = new CatController(catService);

        catController.getCatByName(name);

        Mockito.verify(catService).getCatByName(name);
    }

    @ParameterizedTest
    @ValueSource(strings = {"name"})
    public void getOwnerByNameTest(String name) {
        CatController catController = new CatController(catService);

        catController.getOwnerByName(name);

        Mockito.verify(catService).getOwnerByName(name);
    }

    @ParameterizedTest
    @CsvSource({"cat1, cat2"})
    public void setFriendshipTest(String name1, String name2) {
        CatController catController = new CatController(catService);

        catController.setFriendship(name1, name2);

        Mockito.verify(catService).setFriendship(name1, name2);
    }
}
