package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final OwnerService ownerService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder,
                       OwnerService ownerService,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.ownerService = ownerService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public JwtAuthenticationResponse signUp(OwnerDto ownerDto) {
        ownerDto.setPassword(passwordEncoder.encode(ownerDto.getPassword()));
        return ownerService.addNewOwner(ownerDto);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = ownerService.userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
