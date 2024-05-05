package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class CatController {
    private final AuthService authService;
    private final ICatService catService;
    private final OwnerService ownerService;

    @Autowired
    public CatController(AuthService authService,
                         ICatService catService,
                         OwnerService ownerService) {
        this.authService = authService;
        this.catService = catService;
        this.ownerService = ownerService;
    }

    @PostMapping("/cats")
    public void addCat(@RequestBody CatDto cat) {
        if (!validateDate(cat.getDateOfBirth())) return;
        OwnerDto userDetails = (OwnerDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.equals(userDetails.getRole(), "ROLE_ADMIN")
                || userDetails.getUsername().equals(cat.getOwnerName()))
            catService.addNewCat(cat);
    }

    @PostMapping("/auth/sign-up")
    public JwtAuthenticationResponse addOwner(@RequestBody OwnerDto ownerDto) {
        if (!validateDate(ownerDto.getDateOfBirth())) return null;
        return authService.signUp(ownerDto);
    }

    @PostMapping("/auth/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
        return authService.signIn(request);
    }

    @DeleteMapping("cats/{name}")
    public void deleteCat(@PathVariable String name) {
        CatDto cat = catService.getCatByName(name);
        OwnerDto userDetails = (OwnerDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.equals(userDetails.getRole(), "ROLE_ADMIN")
                || userDetails.getUsername().equals(cat.getOwnerName()))
            catService.deleteCat(name);
    }

    @DeleteMapping("owners/{name}")
    public void deleteOwner(@PathVariable String name) {
        OwnerDto userDetails = (OwnerDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.equals(userDetails.getRole(), "ROLE_ADMIN")
                || userDetails.getUsername().equals(name))
            catService.deleteOwner(name);
    }

    @GetMapping("/cats")
    public List<CatDto> getAllCats() {
        OwnerDto userDetails = (OwnerDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.equals(userDetails.getRole(), "ROLE_ADMIN")) return catService.getAllCats();
        return catService.getAllCatsByOwner(userDetails.getUsername());
    }

    @GetMapping("/owners")
    public List<OwnerDto> getAllOwners() {
        return catService.getAllOwners();
    }

    @GetMapping("/cats/{name}")
    public CatDto getCatByName(@PathVariable String name) {
        OwnerDto userDetails = (OwnerDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CatDto cat = catService.getCatByName(name);
        if (Objects.equals(userDetails.getRole(), "ROLE_ADMIN")
                || userDetails.getUsername().equals(cat.getOwnerName()))
            return cat;
        return null;
    }

    @GetMapping("/cats/breed/{breed}")
    public List<CatDto> getCatsByBreed(@PathVariable String breed) {
        OwnerDto userDetails = (OwnerDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.equals(userDetails.getRole(), "ROLE_ADMIN")) return catService.getAllCatsByBreed(breed);
        return catService.getAllCatsByBreedAndOwner(breed, userDetails.getUsername());
    }

    @GetMapping("/cats/color/{color}")
    public List<CatDto> getCatsByColor(@PathVariable String color) {
        OwnerDto userDetails = (OwnerDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.equals(userDetails.getRole(), "ROLE_ADMIN")) return catService.getAllCatsByColor(color);
        return catService.getAllCatsByColorAndOwner(color, userDetails.getUsername());
    }

    @GetMapping("/owners/{name}")
    public OwnerDto getOwnerByName(@PathVariable String name) {
        return catService.getOwnerByName(name);
    }

    @PutMapping("/give-admin/{name}")
    public void giveAdmin(@PathVariable String name) {
        ownerService.giveAdmin(name);
    }

    @PutMapping("/cats/make-friends/{cat1}/{cat2}")
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
