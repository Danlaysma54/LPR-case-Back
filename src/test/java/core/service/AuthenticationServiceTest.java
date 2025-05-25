package core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import ru.omsu.core.service.authentication.AuthenticationService;
import ru.omsu.core.service.jwt.JwtService;
import ru.omsu.web.model.request.AuthenticationRequestDto;
import ru.omsu.web.model.response.AuthenticationResponseDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    private final String TEST_USERNAME = "testuser";
    private final String TEST_PASSWORD = "testpass";
    private final String TEST_TOKEN = "test.jwt.token";

    @Test
    void authenticate_ShouldReturnToken_WhenAuthenticationSucceeds() {
        // Arrange
        AuthenticationRequestDto request = new AuthenticationRequestDto(TEST_USERNAME, TEST_PASSWORD);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mock(Authentication.class));
        when(jwtService.generateToken(TEST_USERNAME)).thenReturn(TEST_TOKEN);

        // Act
        AuthenticationResponseDto response = authenticationService.authenticate(request);

        // Assert
        assertNotNull(response);
        assertEquals(TEST_TOKEN, response.token());
        verify(authenticationManager).authenticate(argThat(token ->
                token instanceof UsernamePasswordAuthenticationToken &&
                        TEST_USERNAME.equals(token.getPrincipal()) &&
                        TEST_PASSWORD.equals(token.getCredentials()) &&
                        !token.isAuthenticated()
        ));
        verify(jwtService).generateToken(TEST_USERNAME);
    }

    @Test
    void authenticate_ShouldThrow_WhenAuthenticationFails() {
        // Arrange
        AuthenticationRequestDto request = new AuthenticationRequestDto(TEST_USERNAME, TEST_PASSWORD);
        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        // Act & Assert
        assertThrows(BadCredentialsException.class, () -> {
            authenticationService.authenticate(request);
        });
        verify(jwtService, never()).generateToken(any());
    }

    @Test
    void authenticate_ShouldThrow_WhenRequestIsNull() {
        assertThrows(NullPointerException.class, () -> {
            authenticationService.authenticate(null);
        });
    }
    @Test
    void authenticate_ShouldUseCorrectUsernameForTokenGeneration() {
        // Arrange
        String specialUsername = "user@domain.com";
        AuthenticationRequestDto request = new AuthenticationRequestDto(specialUsername, TEST_PASSWORD);
        when(authenticationManager.authenticate(any())).thenReturn(mock(Authentication.class));
        when(jwtService.generateToken(specialUsername)).thenReturn(TEST_TOKEN);

        // Act
        AuthenticationResponseDto response = authenticationService.authenticate(request);

        // Assert
        assertEquals(TEST_TOKEN, response.token());
        verify(jwtService).generateToken(specialUsername);
    }
}