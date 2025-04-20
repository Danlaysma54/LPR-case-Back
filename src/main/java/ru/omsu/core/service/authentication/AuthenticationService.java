package ru.omsu.core.service.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import ru.omsu.core.service.jwt.JwtService;
import ru.omsu.web.model.request.AuthenticationRequestDto;
import ru.omsu.web.model.response.AuthenticationResponseDto;
@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponseDto authenticate(
            final AuthenticationRequestDto request) {

        final var authToken = UsernamePasswordAuthenticationToken
                .unauthenticated(request.username(), request.password());

        final var authentication = authenticationManager
                .authenticate(authToken);

        final var token = jwtService.generateToken(request.username());
        return new AuthenticationResponseDto(token);
    }
}
