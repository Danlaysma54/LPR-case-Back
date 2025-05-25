package core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.omsu.core.repository.project.IProjectRepository;
import ru.omsu.core.repository.tree.TreeRepository;
import ru.omsu.core.service.project.ProjectService;
import ru.omsu.web.model.request.AddProjectRequest;
import ru.omsu.web.model.response.GetProjectResponse;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private IProjectRepository projectRepository;

    @Mock
    private TreeRepository treeRepository;

    @InjectMocks
    private ProjectService projectService;

    private final UUID TEST_PROJECT_ID = UUID.randomUUID();
    private final AddProjectRequest TEST_PROJECT_REQUEST = new AddProjectRequest("","","");
    private final GetProjectResponse TEST_PROJECT_RESPONSE = new GetProjectResponse(null, 0, 0);

    @Test
    void addProject_ShouldReturnUUID_WhenSuccessful() {
        // Arrange
        when(projectRepository.addProject(TEST_PROJECT_REQUEST)).thenReturn(TEST_PROJECT_ID);

        // Act
        UUID result = projectService.addProject(TEST_PROJECT_REQUEST);

        // Assert
        assertEquals(TEST_PROJECT_ID, result);
        verify(projectRepository).addProject(TEST_PROJECT_REQUEST);
    }

    @Test
    void deleteProject_ShouldCallRepository_WhenProjectExists() {
        // Arrange
        doNothing().when(projectRepository).deleteProject(TEST_PROJECT_ID);

        // Act
        projectService.deleteProject(TEST_PROJECT_ID);

        // Assert
        verify(projectRepository).deleteProject(TEST_PROJECT_ID);
    }



    @Test
    void getProjectById_ShouldReturnResponse_WhenProjectExists() {
        // Arrange
        when(projectRepository.getProjectById(TEST_PROJECT_ID)).thenReturn(TEST_PROJECT_RESPONSE.project());

        // Act
        GetProjectResponse response = projectService.getProjectById(TEST_PROJECT_ID);

        // Assert
        assertNotNull(response);
        assertEquals(TEST_PROJECT_RESPONSE.project(), response.project());
        assertEquals(0, response.suitesCount());
        assertEquals(0, response.casesCount());
        verify(projectRepository).getProjectById(TEST_PROJECT_ID);
    }
}