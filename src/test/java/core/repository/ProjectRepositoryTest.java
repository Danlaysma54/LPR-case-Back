package core.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import ru.omsu.core.model.Project;
import ru.omsu.core.repository.project.ProjectRepository;
import ru.omsu.web.model.request.AddProjectRequest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectRepositoryTest {

    private JdbcOperations jdbcOperations;
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        jdbcOperations = mock(JdbcOperations.class);
        projectRepository = new ProjectRepository(jdbcOperations);
    }

    @Test
    void testAddProject_success() {
        UUID expectedId = UUID.randomUUID();
        AddProjectRequest request = new AddProjectRequest("Test Name", "Test Desc", "TEST");

        when(jdbcOperations.queryForObject(
                anyString(),
                any(org.springframework.jdbc.core.RowMapper.class),
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(expectedId);


        UUID result = projectRepository.addProject(request);

        assertEquals(expectedId, result);
        verify(jdbcOperations).queryForObject(anyString(),any(org.springframework.jdbc.core.RowMapper.class),
                 eq("Test Name"), eq("Test Desc"), eq("TEST"));
    }

    @Test
    void testDeleteProject_success() {
        UUID projectId = UUID.randomUUID();

        when(jdbcOperations.update(anyString(), eq(projectId.toString()))).thenReturn(1);

        assertDoesNotThrow(() -> projectRepository.deleteProject(projectId));
        verify(jdbcOperations).update(anyString(), eq(projectId.toString()));
    }

    @Test
    void testDeleteProject_notFound() {
        UUID projectId = UUID.randomUUID();

        when(jdbcOperations.update(anyString(), eq(projectId.toString()))).thenReturn(0);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> projectRepository.deleteProject(projectId));
        assertEquals("Project with this ID doesn't exist", ex.getMessage());
    }

    @Test
    void testGetProjectById_success() {
        UUID projectId = UUID.randomUUID();
        Project expected = new Project(projectId, "Name", "Desc", "Short");

        when(jdbcOperations.queryForObject(anyString(),                 any(org.springframework.jdbc.core.RowMapper.class)
                , eq(projectId))).thenReturn(expected);

        Project result = projectRepository.getProjectById(projectId);

        assertEquals(expected, result);
        verify(jdbcOperations).queryForObject(anyString(),                 any(org.springframework.jdbc.core.RowMapper.class)
                , eq(projectId));
    }

    @Test
    void testGetProjectById_notFound() {
        UUID projectId = UUID.randomUUID();

        when(jdbcOperations.queryForObject(anyString(),                 any(org.springframework.jdbc.core.RowMapper.class)
                , eq(projectId)))
                .thenThrow(new EmptyResultDataAccessException(1));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> projectRepository.getProjectById(projectId));
        assertEquals("Project with this ID doesn't exist", ex.getMessage());
    }
}
