package core.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.jdbc.core.JdbcOperations;
import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.Suite;
import ru.omsu.core.repository.tree.TreeRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TreeRepositoryTest {

    private JdbcOperations jdbcOperations;
    private TreeRepository treeRepository;

    @BeforeEach
    void setUp() {
        jdbcOperations = mock(JdbcOperations.class);
        treeRepository = new TreeRepository(jdbcOperations);
    }

    @Test
    void testGetOneLevelSuites_success() {
        UUID suiteId = UUID.randomUUID();
        Suite suite = new Suite("Suite Name", suiteId, suiteId);

        when(jdbcOperations.query(anyString(),any(org.springframework.jdbc.core.RowMapper.class), any(), any(), any()))
                .thenReturn(List.of(suite));

        List<Suite> result = treeRepository.getOneLevelSuites(suiteId, 1, 10);

        assertEquals(1, result.size());
        assertEquals(suite.getSuiteId(), result.get(0).getSuiteId());
        verify(jdbcOperations).query(anyString(), any(org.springframework.jdbc.core.RowMapper.class), any(), any(), any());
    }

    @Test
    void testGetOneLevelCases_success() {
        UUID suiteId = UUID.randomUUID();
        CaseDTO caseDTO = new CaseDTO("Test Case", UUID.randomUUID());

        when(jdbcOperations.query(anyString(), any(org.springframework.jdbc.core.RowMapper.class), any(), any(), any()))
                .thenReturn(List.of(caseDTO));

        List<CaseDTO> result = treeRepository.getOneLevelCases(suiteId, 2, 10);

        assertEquals(1, result.size());
        assertEquals(caseDTO.caseId(), result.get(0).caseId());
        verify(jdbcOperations).query(anyString(), any(org.springframework.jdbc.core.RowMapper.class), any(), any(), any());
    }

    @Test
    void testGetAllSuites_success() {
        UUID projectId = UUID.randomUUID();
        Suite suite = new Suite("Suite Rec", UUID.randomUUID(), projectId);

        when(jdbcOperations.query(anyString(), any(org.springframework.jdbc.core.RowMapper.class), any()))
                .thenReturn(List.of(suite));

        List<Suite> result = treeRepository.getAllSuites(projectId);

        assertEquals(1, result.size());
        assertEquals(suite.getSuiteName(), result.get(0).getSuiteName());
        verify(jdbcOperations).query(anyString(), any(org.springframework.jdbc.core.RowMapper.class), eq(projectId.toString()));
    }

    @Test
    void testGetOneLevelSuites_empty() {
        when(jdbcOperations.query(anyString(),any(org.springframework.jdbc.core.RowMapper.class), any(), any(), any()))
                .thenReturn(Collections.emptyList());

        List<Suite> result = treeRepository.getOneLevelSuites(UUID.randomUUID(), 1, 10);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetOneLevelCases_empty() {
        when(jdbcOperations.query(anyString(), any(org.springframework.jdbc.core.RowMapper.class), any(), any(), any()))
                .thenReturn(Collections.emptyList());

        List<CaseDTO> result = treeRepository.getOneLevelCases(UUID.randomUUID(), 1, 10);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllSuites_empty() {
        when(jdbcOperations.query(anyString(),any(org.springframework.jdbc.core.RowMapper.class), any()))
                .thenReturn(Collections.emptyList());

        List<Suite> result = treeRepository.getAllSuites(UUID.randomUUID());

        assertTrue(result.isEmpty());
    }
}
