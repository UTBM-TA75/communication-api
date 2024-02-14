package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.domain.dto.auth.LoginResponseDto;
import fr.utbm.fisa.communicationapi.domain.dto.auth.LoginUserDto;
import fr.utbm.fisa.communicationapi.domain.dto.auth.RegisterUserDto;
import fr.utbm.fisa.communicationapi.domain.services.AuthenticationService;
import fr.utbm.fisa.communicationapi.domain.services.JwtService;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Usr;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public Usr register(@RequestBody RegisterUserDto registerUserDto) {
        return authenticationService.signup(registerUserDto);
    }

    @PostMapping("/login")
    public LoginResponseDto authenticate(@RequestBody LoginUserDto loginUserDto) {
        Usr authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        return LoginResponseDto.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();
    }
}
