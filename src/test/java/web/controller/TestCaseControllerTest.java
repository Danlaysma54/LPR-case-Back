package web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.omsu.core.model.TestCase;
import ru.omsu.core.service.testCase.ITestCaseService;
import ru.omsu.web.controllers.TestCaseController;
import ru.omsu.web.model.exception.IdNotExist;
import ru.omsu.web.model.request.EditTestCaseRequest;
import ru.omsu.web.model.request.TestCaseRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.TestCaseTypes;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestCaseControllerTest {

    @Mock
    private ITestCaseService testCaseService;

    @InjectMocks
    private TestCaseController testCaseController;

    @Test
    void addTestCase_ShouldReturnCreatedStatus() {
        // Arrange
        TestCaseRequest request = new TestCaseRequest(UUID.randomUUID(),"HI",UUID.randomUUID(),UUID.randomUUID(),new ArrayList<>());
        AddedEntityResponse expectedTestCase = new AddedEntityResponse(UUID.randomUUID());
        when(testCaseService.addTestCase(request)).thenReturn(expectedTestCase);

        // Act
        ResponseEntity<?> response = testCaseController.addTestCase(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedTestCase, response.getBody());
        verify(testCaseService).addTestCase(request);
    }

    @Test
    void deleteTestCase_ShouldReturnOk_WhenSuccessful() {
        // Arrange
        UUID testCaseId = UUID.randomUUID();
        doNothing().when(testCaseService).deleteTestCase(testCaseId);

        // Act
        ResponseEntity<?> response = testCaseController.deleteTestCase(testCaseId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(testCaseService).deleteTestCase(testCaseId);
    }

    @Test
    void deleteTestCase_ShouldReturnNotFound_WhenIllegalArgument() {
        // Arrange
        UUID testCaseId = UUID.randomUUID();
        String errorMessage = "Test case not found";
        doThrow(new IllegalArgumentException(errorMessage))
                .when(testCaseService).deleteTestCase(testCaseId);

        // Act
        ResponseEntity<?> response = testCaseController.deleteTestCase(testCaseId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
        verify(testCaseService).deleteTestCase(testCaseId);
    }

    @Test
    void editTestCase_ShouldReturnOk_WhenSuccessful() {
        // Arrange
        EditTestCaseRequest editedTestCase = new EditTestCaseRequest(UUID.randomUUID(), "Updated", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        TestCase testCase = new TestCase(UUID.randomUUID(),"HI","layer","auto",UUID.randomUUID());
        when(testCaseService.editTestCase(editedTestCase)).thenReturn(testCase);

        // Act
        ResponseEntity<?> response = testCaseController.editTestCase(editedTestCase);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(editedTestCase, response.getBody());
        verify(testCaseService).editTestCase(editedTestCase);
    }

    @Test
    void editTestCase_ShouldReturnNotFound_WhenIllegalArgument() {
        // Arrange
        EditTestCaseRequest editedTestCase = new EditTestCaseRequest(UUID.randomUUID(), "Updated", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        TestCase testCase = new TestCase(UUID.randomUUID(),"HI","layer","auto",UUID.randomUUID());
        String errorMessage = "Test case not found";
        when(testCaseService.editTestCase(editedTestCase))
                .thenThrow(new IllegalArgumentException(errorMessage));

        // Act
        ResponseEntity<?> response = testCaseController.editTestCase(editedTestCase);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
        verify(testCaseService).editTestCase(editedTestCase);
    }

    @Test
    void getTestCase_ShouldReturnOk_WhenExists() throws IdNotExist {
        // Arrange
        UUID projectId = UUID.randomUUID();
        UUID testCaseId = UUID.randomUUID();
        TestCase expectedTestCase = new TestCase(UUID.randomUUID(),"HI","layer","auto",UUID.randomUUID());
        when(testCaseService.getTestCase(testCaseId)).thenReturn(expectedTestCase);

        // Act
        ResponseEntity<?> response = testCaseController.getTestCase( testCaseId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTestCase, response.getBody());
        verify(testCaseService).getTestCase(testCaseId);
    }

    @Test
    void getTestCase_ShouldReturnNotFound_WhenIdNotExist() throws IdNotExist {
        // Arrange
        UUID projectId = UUID.randomUUID();
        UUID testCaseId = UUID.randomUUID();
        String errorMessage = "Test case not found";
        when(testCaseService.getTestCase(testCaseId))
                .thenThrow(new IdNotExist(errorMessage));

        // Act
        ResponseEntity<?> response = testCaseController.getTestCase( testCaseId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
        verify(testCaseService).getTestCase(testCaseId);
    }

    @Test
    void getTestCaseTypes_ShouldReturnOk() {
        // Arrange
        TestCaseTypes expectedTypes = new TestCaseTypes(new ArrayList<>(),new ArrayList<>()); // Замените на реальный тип возвращаемых данных
        when(testCaseService.getTestCaseTypes()).thenReturn(expectedTypes);

        // Act
        ResponseEntity<?> response = testCaseController.getTestCaseTypes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTypes, response.getBody());
        verify(testCaseService).getTestCaseTypes();
    }
}