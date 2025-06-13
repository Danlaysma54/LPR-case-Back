package web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import ru.omsu.core.service.registration.UserRegistrationService;
import ru.omsu.web.controllers.RegistrationController;
import ru.omsu.web.model.request.RegistrationRequestDto;
import ru.omsu.web.model.response.ErrorResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

    @Mock
    private UserRegistrationService userRegistrationService;

    @InjectMocks
    private RegistrationController registrationController;

    @Test
    void registerUser_ShouldReturnCreatedStatus_WhenSuccessful() {
        // Arrange
        RegistrationRequestDto requestDto = new RegistrationRequestDto(
                "test@example.com",
                "password123"
        );

        doNothing().when(userRegistrationService).registerUser(requestDto);

        // Act
        ResponseEntity<?> response = registrationController.registerUser(requestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
        verify(userRegistrationService, times(1)).registerUser(requestDto);
    }

    @Test
    void registerUser_ShouldReturnConflictWithError_WhenIllegalArgumentException() {
        // Arrange
        RegistrationRequestDto requestDto = new RegistrationRequestDto(
                "existing@example.com",
                "password123");
        String errorMessage = "User already exists";

        doThrow(new IllegalArgumentException(errorMessage))
                .when(userRegistrationService).registerUser(requestDto);

        // Act
        ResponseEntity<?> response = registrationController.registerUser(requestDto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals(errorMessage, ((ErrorResponse) response.getBody()).messageError());
        verify(userRegistrationService, times(1)).registerUser(requestDto);
    }
}