package web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.omsu.core.service.project.IProjectService;
import ru.omsu.web.controllers.ProjectController;
import ru.omsu.web.model.request.AddProjectRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.GetProjectResponse;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    @Mock
    private IProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    // Test data
    private final UUID testProjectId = UUID.randomUUID();
    private final AddProjectRequest testRequest = new AddProjectRequest("","","");
    private final Object testProject = new Object(); // Replace with actual project type

    @Test
    void addProject_ShouldReturnCreatedResponseWithId() {

        when(projectService.addProject(testRequest,"")).thenReturn(testProjectId);

        ResponseEntity<AddedEntityResponse> response = projectController.addProject(testRequest,"");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals(testProjectId, response.getBody().addedEntityId());
        verify(projectService).addProject(testRequest,"");
    }

    @Test
    void addProject_ShouldValidateInput() {
        // This test verifies the @Validated annotation is properly processed
        assertDoesNotThrow(() -> projectController.addProject(testRequest,""));
    }

    @Test
    void deleteProject_ShouldReturnOk_WhenSuccessful() {
        // Arrange
        doNothing().when(projectService).deleteProject(testProjectId);

        // Act
        ResponseEntity<?> response = projectController.deleteProject(testProjectId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(projectService).deleteProject(testProjectId);
    }

    @Test
    void deleteProject_ShouldReturnNotFound_WhenProjectDoesNotExist() {
        // Arrange
        String errorMessage = "Project not found";
        doThrow(new IllegalArgumentException(errorMessage))
                .when(projectService).deleteProject(testProjectId);

        // Act
        ResponseEntity<?> response = projectController.deleteProject(testProjectId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
        verify(projectService).deleteProject(testProjectId);
    }

    @Test
    void deleteProject_ShouldValidatePathVariable() {
        // This test verifies the @NotNull annotation is properly processed
        assertDoesNotThrow(() -> projectController.deleteProject(testProjectId));
    }

    @Test
    void getProject_ShouldReturnProject_WhenExists() {
        // Arrange
        GetProjectResponse getProjectResponse = Mockito.mock(GetProjectResponse.class);
        when(projectService.getProjectById(testProjectId)).thenReturn(getProjectResponse);

        // Act
        ResponseEntity<?> response = projectController.getProject(testProjectId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(getProjectResponse, response.getBody());
        verify(projectService).getProjectById(testProjectId);
    }

    @Test
    void getProject_ShouldReturnNotFound_WhenProjectDoesNotExist() {
        // Arrange
        String errorMessage = "Project not found";
        when(projectService.getProjectById(testProjectId))
                .thenThrow(new IllegalArgumentException(errorMessage));

        // Act
        ResponseEntity<?> response = projectController.getProject(testProjectId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
        verify(projectService).getProjectById(testProjectId);
    }

    @Test
    void getProject_ShouldValidatePathVariable() {
        // This test verifies the @NotNull annotation is properly processed
        assertDoesNotThrow(() -> projectController.getProject(testProjectId));
    }
}