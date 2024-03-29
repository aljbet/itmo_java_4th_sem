import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "color")
    private String color;

    @Column(name = "breed")
    private String breed;

    @ManyToOne(fetch = FetchType.EAGER)
    private Owner owner;

    @ManyToMany(targetEntity = Cat.class, fetch = FetchType.EAGER)
    @JoinTable(name="friends")
    private Set<Cat> friends;

    public String toString() {
        String friendsStr = "";
        for (Cat cat : friends) {
            friendsStr += cat.getName() + ", ";
        }
        if (!friendsStr.isEmpty()) friendsStr = friendsStr.substring(0, friendsStr.length()-2);
        return "Cat: name=" + name + ", dateOfBirth=" + dateOfBirth +
                ", color=" + color + ", breed=" + breed +
                ", owner=" + owner.getName() + ", friends=" + friendsStr;
    }
}
