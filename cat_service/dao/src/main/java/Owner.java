import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="owners")
public class Owner {
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="date_of_birth")
    private String dateOfBirth;

    @OneToMany(mappedBy = "owner")
    private List<Cat> cats;
}
