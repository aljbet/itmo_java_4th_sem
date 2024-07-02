package app;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Vector;

@Data
@Entity
@NoArgsConstructor
@Table(name = "owners")
public class Owner {
    public Owner(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        cats = new Vector<>();
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private List<Cat> cats;
}
