package app;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        if (Objects.equals(role, "ROLE_ADMIN")) this.role = Role.ROLE_ADMIN;
        if (Objects.equals(role, "ROLE_USER")) this.role = Role.ROLE_USER;
    }

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
