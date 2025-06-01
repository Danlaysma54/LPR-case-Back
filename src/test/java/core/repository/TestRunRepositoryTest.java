package core.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.jdbc.core.JdbcOperations;
import ru.omsu.core.model.TestRun;
import ru.omsu.core.model.TestRunDTO;
import ru.omsu.core.repository.testRun.TestRunRepository;
import ru.omsu.web.model.request.AddTestRunRequest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestRunRepositoryTest {

    private JdbcOperations jdbcOperations;
    private TestRunRepository repository;

    @BeforeEach
    void setUp() {
        jdbcOperations = mock(JdbcOperations.class);
        repository = new TestRunRepository(jdbcOperations);
    }

    @Test
    void getTestRun_success() {
        TestRunDTO dto = new TestRunDTO(UUID.randomUUID(), "Test Run 1");

        when(jdbcOperations.query(
                eq("Select test_name,test_run_id from test_run"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<TestRunDTO>>any()
        )).thenReturn(List.of(dto));

        List<TestRunDTO> result = repository.getTestRun();

        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
    }

    @Test
    void addTestRun_success() {
        AddTestRunRequest request = mock(AddTestRunRequest.class);
        UUID expectedId = UUID.randomUUID();

        when(request.testRunName()).thenReturn("New Test Run");
        when(request.testPlanId()).thenReturn(UUID.randomUUID());

        when(jdbcOperations.queryForObject(
                anyString(),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<UUID>>any(),
                any(), any()
        )).thenReturn(expectedId);

        UUID actualId = repository.addTestRun(request);

        assertEquals(expectedId, actualId);
    }

    @Test
    void editTestRun_success() {
        TestRun testRun = mock(TestRun.class);

        when(testRun.testName()).thenReturn("Updated Name");
        when(testRun.testPlanId()).thenReturn(UUID.randomUUID());
        when(testRun.testRunId()).thenReturn(UUID.randomUUID());

        when(jdbcOperations.update(
                anyString(),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<UUID>>any(),
                any(), any()
        )).thenReturn(1);

        assertDoesNotThrow(() -> repository.editTestRun(testRun));
    }

    @Test
    void getTestRunById_success() {
        UUID testRunId = UUID.randomUUID();
        TestRun expected = new TestRun(testRunId, UUID.randomUUID(), "Test Run");

        when(jdbcOperations.queryForObject(
                eq("select test_name,test_plan_id from test_run where test_run_id=?"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<TestRun>>any(),
                eq(testRunId)
        )).thenReturn(expected);

        TestRun actual = repository.getTestRunById(testRunId);

        assertEquals(expected, actual);
    }

    @Test
    void deleteTestRun_success() {
        UUID testRunId = UUID.randomUUID();

        when(jdbcOperations.update(
                eq("delete from test_run where test_run_id=?"),
                eq(testRunId)
        )).thenReturn(1);

        assertDoesNotThrow(() -> repository.deleteTestRun(testRunId));
    }
}
