package fr.utbm.fisa.communicationapi.domain.services;

import fr.utbm.fisa.communicationapi.domain.dto.auth.LoginUserDto;
import fr.utbm.fisa.communicationapi.domain.dto.auth.RegisterUserDto;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Usr;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsrRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public Usr signup(RegisterUserDto input) {
        Usr user = new Usr();
        user.setEmail(input.getEmail());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public Usr authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}
