package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final JwtService jwtService;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository,
                        JwtService jwtService) {
        this.ownerRepository = ownerRepository;
        this.jwtService = jwtService;
    }

    public JwtAuthenticationResponse addNewOwner(OwnerDto ownerDto) {
        Owner owner = ownerDto.getOwner();
        owner.setRole(Role.ROLE_USER);
        ownerRepository.save(owner);
        var jwt = jwtService.generateToken(ownerDto);
        return new JwtAuthenticationResponse(jwt);
    }

    public OwnerDto getByUsername(String username) {
        return new OwnerDto(ownerRepository.findOwnerByUsername(username));
    }

    public Owner getCurrentOwner() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username).getOwner();
    }

    public void giveAdmin(String username) {
        Owner owner = ownerRepository.findOwnerByUsername(username);
        owner.setRole(Role.ROLE_ADMIN);
        ownerRepository.save(owner);
    }

    @Deprecated
    public void getAdmin() {
        var owner = getCurrentOwner();
        owner.setRole(Role.ROLE_ADMIN);
        ownerRepository.save(owner);
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }
}
