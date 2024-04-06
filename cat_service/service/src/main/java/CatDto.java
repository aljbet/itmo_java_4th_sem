import lombok.Data;

import java.util.List;
import java.util.Vector;

@Data
public class CatDto {
    public CatDto(Cat cat) {
        this.name = cat.getName();
        this.dateOfBirth = cat.getDateOfBirth();
        this.color = cat.getColor();
        this.breed = cat.getBreed();
        this.ownerName = cat.getOwner().getName();
        this.friends = new Vector<>();
        for (Cat friend : cat.getFriends())
        {
            this.friends.add(friend.getName());
        }
    }

    public CatDto(String name, String dateOfBirth, String color, String breed, String ownerName) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.color = color;
        this.breed = breed;
        this.ownerName = ownerName;
    }

    private String name;

    private String dateOfBirth;

    private String color;

    private String breed;

    private String ownerName;

    private List<String> friends;

    public Cat getCat(OwnerDao ownerDao) {
        Owner owner = ownerDao.getByName(ownerName);
        return new Cat(name, dateOfBirth, color, breed, owner);
    }
}
