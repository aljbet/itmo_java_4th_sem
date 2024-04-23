package app;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Vector;

@Data
@Entity
@Table(name = "owners")
public class Owner {
    public Owner() {
        cats = new Vector<>();
    }
    public Owner(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        cats = new Vector<>();
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private List<Cat> cats;
}
