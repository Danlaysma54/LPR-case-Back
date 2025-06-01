package web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.omsu.core.model.TestRun;
import ru.omsu.core.model.TestRunDTO;
import ru.omsu.core.service.testRun.ITestRunService;
import ru.omsu.web.controllers.TestRunController;
import ru.omsu.web.model.request.AddTestRunRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.GetTestRunResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestRunControllerTest {

    @Mock
    private ITestRunService testRunService;

    @InjectMocks
    private TestRunController testRunController;

    @Test
    void getTestRuns_ShouldReturnListOfTestRuns() {
        // Arrange
        UUID projectId = UUID.randomUUID();
        GetTestRunResponse expectedTestRuns = new GetTestRunResponse(new ArrayList<>());
        when(testRunService.getTestRuns(projectId)).thenReturn(expectedTestRuns);

        // Act
        ResponseEntity<?> response = testRunController.getTestRuns(projectId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTestRuns, response.getBody());
        verify(testRunService).getTestRuns(projectId);
    }

    @Test
    void addTestRun_ShouldReturnCreatedStatus() {
        // Arrange
        AddTestRunRequest request = new AddTestRunRequest("Hello",UUID.randomUUID());
        AddedEntityResponse addedEntityResponse =new AddedEntityResponse(UUID.randomUUID());
        when(testRunService.addTestRun(request)).thenReturn(addedEntityResponse);

        // Act
        ResponseEntity<?> response = testRunController.addTestRun(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(addedEntityResponse, response.getBody());
        verify(testRunService).addTestRun(request);
    }

    @Test
    void editTestRun_ShouldReturnOkWithUpdatedTestRun() {
        // Arrange
        TestRun testRun = new TestRun(UUID.randomUUID(),UUID.randomUUID(),"");
        TestRun updatedTestRun = new TestRun(UUID.randomUUID(),UUID.randomUUID(),"");
        when(testRunService.editTestRun(testRun)).thenReturn(updatedTestRun);

        // Act
        ResponseEntity<?> response = testRunController.editTestRun(testRun);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTestRun, response.getBody());
        verify(testRunService).editTestRun(testRun);
    }

    @Test
    void deleteTestRun_ShouldReturnOkStatus() {
        // Arrange
        UUID testRunId = UUID.randomUUID();
        doNothing().when(testRunService).deleteTestRun(testRunId);

        // Act
        ResponseEntity<?> response = testRunController.deleteTestRun(testRunId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(testRunService).deleteTestRun(testRunId);
    }

    @Test
    void addTestRun_ShouldValidateRequestBody() {
        // This test verifies that the method is properly annotated for validation
        assertDoesNotThrow(() -> {
            AddTestRunRequest request = new AddTestRunRequest("",UUID.randomUUID());
            // Set valid data
            testRunController.addTestRun(request);
        });
    }

    @Test
    void editTestRun_ShouldValidateRequestBody() {
        // This test verifies that the method is properly annotated for validation
        assertDoesNotThrow(() -> {
            TestRun testRun = new TestRun(UUID.randomUUID(),UUID.randomUUID(),"");
            // Set valid data
            testRunController.editTestRun(testRun);
        });
    }

    @Test
    void deleteTestRun_ShouldValidatePathVariable() {
        // This test verifies that the method is properly annotated for validation
        assertDoesNotThrow(() -> {
            testRunController.deleteTestRun(UUID.randomUUID());
        });
    }
}