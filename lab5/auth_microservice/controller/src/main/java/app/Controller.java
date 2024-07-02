package app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class Controller {
    @PostMapping("/auth/sign-up")
    public ResponseEntity<String> addOwner(@RequestBody OwnerDto ownerDto) {
        if (!validateDate(ownerDto.getDateOfBirth())) return null;
        String result = authService.signUp(ownerDto);
        if (result == null) return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("Token: " + result, HttpStatus.OK);
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
