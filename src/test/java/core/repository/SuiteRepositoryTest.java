package core.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import ru.omsu.core.model.Suite;
import ru.omsu.core.repository.suite.SuiteRepository;
import ru.omsu.web.model.exception.IdNotExist;
import ru.omsu.web.model.request.AddSuiteRequest;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class SuiteRepositoryTest {

    private JdbcOperations jdbcOperations;
    private SuiteRepository suiteRepository;

    @BeforeEach
    void setUp() {
        jdbcOperations = mock(JdbcOperations.class);
        suiteRepository = new SuiteRepository(jdbcOperations);
    }

    @Test
    void testGetSuite_success() {
        UUID suiteId = UUID.randomUUID();
        UUID rootId = UUID.randomUUID();
        String suiteName = "My Suite";

        Suite expected = new Suite(suiteName, suiteId, rootId);

        when(jdbcOperations.queryForObject(
                eq("select suite_name,suite_id,suite_root_id from suite where suite_id=?"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<Suite>>any(),
                eq(suiteId)
        )).thenReturn(expected);

        Suite result = suiteRepository.getSuite(suiteId);

        assertEquals(expected, result);
    }

    @Test
    void testGetSuite_notFound() {
        UUID suiteId = UUID.randomUUID();

        when(jdbcOperations.queryForObject(
                anyString(),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<Suite>>any(),
                eq(suiteId)
        )).thenThrow(EmptyResultDataAccessException.class);

        assertThrows(IdNotExist.class, () -> suiteRepository.getSuite(suiteId));
    }

    @Test
    void testAddSuite_success() {
        UUID expectedId = UUID.randomUUID();
        Suite request = new Suite("Suite A", UUID.randomUUID(),UUID.randomUUID());

        when(jdbcOperations.queryForObject(
                eq("INSERT INTO suite(suite_name,suite_root_id) VALUES (?,?) RETURNING suite_id"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<UUID>>any(),
                eq(request.getSuiteName()), eq(request.getSuiteRootId())
        )).thenReturn(expectedId);

        UUID result = suiteRepository.addSuite(request);

        assertEquals(expectedId, result);
    }

    @Test
    void testEditSuite_success() {
        Suite suite = new Suite("Updated Suite", UUID.randomUUID(), UUID.randomUUID());

        when(jdbcOperations.update(anyString(), any(), any(), any())).thenReturn(1);

        assertDoesNotThrow(() -> suiteRepository.editSuite(suite));
    }

    @Test
    void testEditSuite_notFound() {
        Suite suite = new Suite("Updated Suite", UUID.randomUUID(), UUID.randomUUID());

        when(jdbcOperations.update(anyString(), any(), any(), any())).thenReturn(0);

        assertThrows(IdNotExist.class, () -> suiteRepository.editSuite(suite));
    }

    @Test
    void testDeleteSuite_success() {
        UUID suiteId = UUID.randomUUID();

        when(jdbcOperations.update(anyString(), eq(suiteId))).thenReturn(1);

        assertDoesNotThrow(() -> suiteRepository.deleteSuite(suiteId));
    }

    @Test
    void testDeleteSuite_notFound() {
        UUID suiteId = UUID.randomUUID();

        when(jdbcOperations.update(anyString(), eq(suiteId))).thenReturn(0);

        assertThrows(IdNotExist.class, () -> suiteRepository.deleteSuite(suiteId));
    }

    @Test
    void testGetAllSuitesInProject_success() {
        UUID projectId = UUID.randomUUID();
        UUID suiteId = UUID.randomUUID();

        when(jdbcOperations.query(
                eq("select suite_id from suites_in_project where project_id=?"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<UUID>>any(),
                eq(projectId.toString())
        )).thenReturn(List.of(suiteId));

        List<UUID> result = suiteRepository.getAllSuitesInProject(projectId);

        assertEquals(1, result.size());
        assertEquals(suiteId, result.get(0));
    }

    @Test
    void testGetAllSuitesInProject_empty() {
        UUID projectId = UUID.randomUUID();

        when(jdbcOperations.query(
                anyString(),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<UUID>>any(),
                eq(projectId.toString())
        )).thenReturn(Collections.emptyList());

        List<UUID> result = suiteRepository.getAllSuitesInProject(projectId);

        assertTrue(result.isEmpty());
    }
}
