package ru.omsu.core.service.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import ru.omsu.core.service.authentication.CustomUserDetails;

import java.time.Duration;
import java.time.Instant;

public class JwtService {
    private final String issuer;

    private final Duration ttl;

    private final JwtEncoder jwtEncoder;

    public JwtService(String issuer, Duration ttl, JwtEncoder jwtEncoder) {
        this.issuer = issuer;
        this.ttl = ttl;
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String userId = userDetails.getUserId();  // Получаем userId из CustomUserDetails

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(username)
                .issuer(issuer)
                .expiresAt(Instant.now().plus(ttl))
                .claim("userId", userId)  // Добавляем в токен
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
