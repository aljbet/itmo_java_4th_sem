import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="cats")
public class Cat {
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="date_of_birth")
    private String dateOfBirth;

    @Column(name="color")
    private String color;

    @Column(name="breed")
    private String breed;

    @ManyToOne
    private Owner owner;

    @ManyToMany
    private List<Cat> friends;
}
