import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

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

    @OneToMany(mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Cat> cats;

    public String toString() {
        String catsStr = "";
        for (Cat cat : cats) {
            catsStr += cat.getName() + ", ";
        }
        if (!catsStr.isEmpty()) catsStr = catsStr.substring(0, catsStr.length()-2);
        return "Owner: name=" + name + ", dateOfBirth=" + dateOfBirth +
                ", cats=" + catsStr;
    }
}
