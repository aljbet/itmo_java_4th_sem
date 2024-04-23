package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class CatController {
    private final ICatService catService;
    @Autowired
    public CatController(ICatService catService) {
        this.catService = catService;
    }

    @PostMapping("/cats")
    public void addCat(@RequestBody CatDto cat) {
        if (validateDate(cat.getDateOfBirth())) catService.addNewCat(cat);
    }

    @PostMapping("/owners")
    public void addOwner(@RequestBody OwnerDto owner) {
        if (validateDate(owner.getDateOfBirth())) catService.addNewOwner(owner);
    }

    @DeleteMapping("cats/{name}")
    public void deleteCat(@PathVariable String name) {
        catService.deleteCat(name);
    }

    @DeleteMapping("owners/{name}")
    public void deleteOwner(@PathVariable String name) {
        catService.deleteOwner(name);
    }

    @GetMapping("/cats")
    public List<CatDto> getAllCats() {
        return catService.getAllCats();
    }

    @GetMapping("/owners")
    public List<OwnerDto> getAllOwners() {
        return catService.getAllOwners();
    }

    @GetMapping("/cats/{name}")
    public CatDto getCatByName(@PathVariable String name) {
        return catService.getCatByName(name);
    }

    @GetMapping("/owners/{name}")
    public OwnerDto getOwnerByName(@PathVariable String name) {
        return catService.getOwnerByName(name);
    }

    @PutMapping("/cats/make-friends-{cat1}-{cat2}")
    public void setFriendship(@PathVariable("cat1") String cat1, @PathVariable("cat2") String cat2) {
        if (!Objects.equals(cat1, cat2)) catService.setFriendship(cat1, cat2);
    }

    private static boolean validateDate(String dateOfBirth) {
        Pattern pattern = Pattern.compile("[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}");
        Matcher matcher = pattern.matcher(dateOfBirth);
        if (!matcher.find()) return false;
        int day = Integer.parseInt(dateOfBirth.substring(0, 2));
        int month = Integer.parseInt(dateOfBirth.substring(3, 5));
        int year = Integer.parseInt(dateOfBirth.substring(6, 10));
        return day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 1900 && year <= 2024;
    }
}
