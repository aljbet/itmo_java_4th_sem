package app;

import lombok.Data;

import java.util.List;
import java.util.Vector;

@Data
public class OwnerDto {
    public OwnerDto(Owner owner) {
        this.name = owner.getName();
        this.dateOfBirth = owner.getDateOfBirth();
        this.cats = new Vector<>();
        for (Cat cat : owner.getCats()) {
            this.cats.add(cat.getName());
        }
    }

    public OwnerDto(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    private String name;

    private String dateOfBirth;

    private List<String> cats;

    public Owner getOwner() {
        return new Owner(name, dateOfBirth);
    }
}
