package web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import ru.omsu.core.service.authentication.AuthenticationService;
import ru.omsu.web.controllers.AuthController;
import ru.omsu.web.model.request.AuthenticationRequestDto;
import ru.omsu.web.model.response.AuthenticationResponseDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthController authController;

    @Test
    void authenticate_ShouldReturnResponseEntityWithAuthResponse() {
        // Arrange
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto("username", "password");
        AuthenticationResponseDto expectedResponse = new AuthenticationResponseDto("token");

        when(authenticationService.authenticate(requestDto))
                .thenReturn(expectedResponse);

        // Act
        ResponseEntity<AuthenticationResponseDto> response =
                authController.authenticate(requestDto);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResponse, response.getBody());
        verify(authenticationService, times(1)).authenticate(requestDto);
    }

    @Test
    void authenticate_ShouldCallServiceWithCorrectParameters() {
        // Arrange
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto("user", "pass");
        AuthenticationResponseDto serviceResponse = new AuthenticationResponseDto("token123");

        when(authenticationService.authenticate(any(AuthenticationRequestDto.class)))
                .thenReturn(serviceResponse);

        // Act
        authController.authenticate(requestDto);

        // Assert
        verify(authenticationService).authenticate(requestDto);
    }
}