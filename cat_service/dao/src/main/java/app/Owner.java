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

    public Owner(String name, String dateOfBirth, String password) {
        this.username = name;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        cats = new Vector<>();
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private List<Cat> cats;
}
