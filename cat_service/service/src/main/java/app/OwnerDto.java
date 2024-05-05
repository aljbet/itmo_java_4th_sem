package app;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

@Data
public class OwnerDto implements UserDetails {
    public OwnerDto(Owner owner) {
        this.username = owner.getUsername();
        this.dateOfBirth = owner.getDateOfBirth();
        this.password = owner.getPassword();
        this.role = owner.getRole().name();
        this.cats = new Vector<>();
        for (Cat cat : owner.getCats()) {
            this.cats.add(cat.getName());
        }
    }

    public OwnerDto(String name, String dateOfBirth, String password) {
        this.username = name;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    private String username;
    private String dateOfBirth;
    private String password;
    private String role;

    private List<String> cats;

    public Owner getOwner() {
        return new Owner(username, dateOfBirth, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
