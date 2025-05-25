package core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import ru.omsu.core.service.jwt.JwtService;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @Mock
    private JwtEncoder jwtEncoder;

    @InjectMocks
    private JwtService jwtService;

    private final String TEST_ISSUER = "test-issuer";
    private final String TEST_USERNAME = "testuser";

    @Test
    void generateToken_ShouldIncludeCorrectExpiration() {
        // Arrange
        Duration customTtl = Duration.ofMinutes(30);
        jwtService = new JwtService(TEST_ISSUER, customTtl, jwtEncoder);
        Jwt mockJwt = mock(Jwt.class);
        when(jwtEncoder.encode(any())).thenReturn(mockJwt);

        // Act
        jwtService.generateToken(TEST_USERNAME);

        // Assert
        verify(jwtEncoder).encode(argThat(parameters -> {
            Instant expiresAt = parameters.getClaims().getExpiresAt();
            return expiresAt.isAfter(Instant.now()) &&
                    expiresAt.isBefore(Instant.now().plus(customTtl.plusSeconds(1)));
        }));
    }
}