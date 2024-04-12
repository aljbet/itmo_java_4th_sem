package app;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Vector;

@Data
@Entity
@Table(name = "cats")
public class Cat {
    public Cat() {
        friends = new Vector<>();
    }

    public Cat(String name, String dateOfBirth, String color, String breed, Owner owner) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.color = color;
        this.breed = breed;
        this.owner = owner;
        friends = new Vector<>();
    }
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
    private List<Cat> friends;
}
