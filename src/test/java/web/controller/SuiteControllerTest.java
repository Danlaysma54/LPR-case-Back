package web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.omsu.core.model.Suite;
import ru.omsu.core.service.suite.ISuiteService;
import ru.omsu.web.controllers.SuiteController;
import ru.omsu.web.model.exception.IdNotExist;
import ru.omsu.web.model.request.AddSuiteRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.ErrorResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SuiteControllerTest {

    @Mock
    private ISuiteService suiteService;

    @InjectMocks
    private SuiteController suiteController;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void addSuite_ShouldReturnCreated_WhenValidRequest() throws IdNotExist {
        // Arrange
        AddSuiteRequest validRequest = new AddSuiteRequest(
                "Valid Suite",UUID.randomUUID());
        UUID expectedId = UUID.randomUUID();

        when(suiteService.addSuite(validRequest)).thenReturn(expectedId);

        // Act
        ResponseEntity<?> response = suiteController.addSuite(validRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody() instanceof AddedEntityResponse);
        assertEquals(expectedId, ((AddedEntityResponse) response.getBody()).addedEntityId());
        verify(suiteService).addSuite(validRequest);
    }

    @Test
    void addSuite_ShouldReturnBadRequest_WhenInvalidRequest() {
        // Arrange
        AddSuiteRequest invalidRequest = new AddSuiteRequest(
                null,  null); // Все поля невалидные

        // Act
        ResponseEntity<?> response = suiteController.addSuite(invalidRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertFalse(((ErrorResponse) response.getBody()).messageError().isEmpty());
        verifyNoInteractions(suiteService);
    }

    @Test
    void addSuite_ShouldReturnNotFound_WhenIdNotExist() throws IdNotExist {
        // Arrange
        AddSuiteRequest validRequest = new AddSuiteRequest(
                "Valid Suite",UUID.randomUUID());
        String errorMessage = "Project not found";

        when(suiteService.addSuite(validRequest))
                .thenThrow(new IdNotExist(errorMessage));

        // Act
        ResponseEntity<?> response = suiteController.addSuite(validRequest);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals(errorMessage, ((ErrorResponse) response.getBody()).messageError());
    }

    @Test
    void editSuite_ShouldReturnOk_WhenValidSuite() throws IdNotExist {
        // Arrange
        Suite validSuite = new Suite("Updated Suite",UUID.randomUUID(),  UUID.randomUUID());
        when(suiteService.editSuite(validSuite)).thenReturn(validSuite);

        // Act
        ResponseEntity<?> response = suiteController.editSuite(validSuite);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(validSuite, response.getBody());
        verify(suiteService).editSuite(validSuite);
    }

    @Test
    void editSuite_ShouldReturnBadRequest_WhenInvalidSuite() {
        // Arrange
        Suite invalidSuite = new Suite(null, null, null); // Невалидные данные

        // Act
        ResponseEntity<?> response = suiteController.editSuite(invalidSuite);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        verifyNoInteractions(suiteService);
    }

    @Test
    void editSuite_ShouldReturnNotFound_WhenIdNotExist() throws IdNotExist {
        // Arrange
        Suite validSuite = new Suite("Updated Suite",UUID.randomUUID(),  UUID.randomUUID());
        String errorMessage = "Suite not found";

        when(suiteService.editSuite(validSuite))
                .thenThrow(new IdNotExist(errorMessage));

        // Act
        ResponseEntity<?> response = suiteController.editSuite(validSuite);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals(errorMessage, ((ErrorResponse) response.getBody()).messageError());
    }

    @Test
    void deleteSuite_ShouldReturnNoContent_WhenSuccess() throws IdNotExist {
        // Arrange
        UUID projectId = UUID.randomUUID();
        UUID suiteId = UUID.randomUUID();
        doNothing().when(suiteService).deleteSuite(suiteId);

        // Act
        ResponseEntity<?> response = suiteController.deleteSuite(projectId, suiteId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(suiteService).deleteSuite(suiteId);
    }

    @Test
    void deleteSuite_ShouldReturnNotFound_WhenIdNotExist() throws IdNotExist {
        // Arrange
        UUID projectId = UUID.randomUUID();
        UUID suiteId = UUID.randomUUID();
        String errorMessage = "Suite not found";

        doThrow(new IdNotExist(errorMessage))
                .when(suiteService).deleteSuite(suiteId);

        // Act
        ResponseEntity<?> response = suiteController.deleteSuite(projectId, suiteId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals(errorMessage, ((ErrorResponse) response.getBody()).messageError());
    }
}