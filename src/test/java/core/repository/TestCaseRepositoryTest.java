package core.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import ru.omsu.core.model.Automation;
import ru.omsu.core.model.Layer;
import ru.omsu.core.model.Step;
import ru.omsu.core.model.TestCase;
import ru.omsu.core.repository.testCase.TestCaseRepository;
import ru.omsu.web.model.request.EditTestCaseRequest;
import ru.omsu.web.model.request.StepsRequest;
import ru.omsu.web.model.request.TestCaseRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestCaseRepositoryTest {

    private JdbcOperations jdbcOperations;
    private TestCaseRepository repository;

    @BeforeEach
    void setUp() {
        jdbcOperations = mock(JdbcOperations.class);
        repository = new TestCaseRepository(jdbcOperations);
    }

    @Test
    void addTestCase_success() {
        TestCaseRequest request = new TestCaseRequest(UUID.randomUUID(),"Test case 1", UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>());
        UUID expectedId = UUID.randomUUID();

        when(jdbcOperations.queryForObject(
                eq("INSERT INTO test_case(test_case_name,suite_id,automation_status_id,layer_id) VALUES (?,?,?,?) RETURNING test_case_id"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<UUID>>any(),
                eq(request.testCaseName()),
                eq(request.suiteId()),
                eq(request.isAutomatedId()),
                eq(request.layerId())
        )).thenReturn(expectedId);

        UUID actualId = repository.addTestCase(request);

        assertEquals(expectedId, actualId);
    }

    @Test
    void getTestCase_success() {
        UUID testCaseId = UUID.randomUUID();
        TestCase expected = new TestCase(testCaseId, "Test name", "Layer1", "Automated", UUID.randomUUID());

        when(jdbcOperations.queryForObject(
                contains("select test_case_id,test_case_name,suite_id,layer_name,automation_name"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<TestCase>>any(),
                eq(testCaseId)
        )).thenReturn(expected);

        TestCase actual = repository.getTestCase(testCaseId);

        assertEquals(expected, actual);
    }

    @Test
    void deleteTestCase_success() {
        UUID testCaseId = UUID.randomUUID();

        when(jdbcOperations.update(
                eq("DELETE FROM test_case where test_case_id = CAST(? AS UUID)"),
                eq(testCaseId.toString())
        )).thenReturn(1);

        assertDoesNotThrow(() -> repository.deleteTestCase(testCaseId));
    }

    @Test
    void deleteTestCase_notFound() {
        UUID testCaseId = UUID.randomUUID();

        when(jdbcOperations.update(anyString(), anyString())).thenReturn(0);

        assertThrows(IllegalArgumentException.class, () -> repository.deleteTestCase(testCaseId));
    }

    @Test
    void editTestCase_success() {
        EditTestCaseRequest testCase = new EditTestCaseRequest(UUID.randomUUID(), "Updated", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        when(jdbcOperations.update(
                anyString(),
                eq(testCase.getTestCaseName()),
                eq(testCase.getSuiteId()),
                eq(testCase.getIsAutomated()),
                eq(testCase.getLayer()),
                eq(testCase.getTestCaseId())
        )).thenReturn(1);

        assertDoesNotThrow(() -> repository.editTestCase(testCase));
    }

    @Test
    void editTestCase_notFound() {
        EditTestCaseRequest testCase = new EditTestCaseRequest(UUID.randomUUID(), "Updated", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        when(jdbcOperations.update(anyString(), any(), any(), any(), any(), any())).thenReturn(0);

        assertThrows(IllegalArgumentException.class, () -> repository.editTestCase(testCase));
    }

    @Test
    void getTestCaseSteps_success() {
        UUID testCaseId = UUID.randomUUID();
        Step step = new Step(UUID.randomUUID(), "desc", "data", "result", 1);

        when(jdbcOperations.query(
                eq("SELECT test_step_id,step_description,step_data,step_result,step_number from test_step where test_case_id=?"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<Step>>any(),
                eq(testCaseId)
        )).thenReturn(List.of(step));

        List<Step> steps = repository.getTestCaseSteps(testCaseId);

        assertEquals(1, steps.size());
        assertEquals(step, steps.get(0));
    }

    @Test
    void getTestCaseLayers_success() {
        Layer layer = new Layer("LayerName", UUID.randomUUID());

        when(jdbcOperations.query(
                eq("SELECT layer_id,layer_name from layer"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<Layer>>any()
        )).thenReturn(List.of(layer));

        List<Layer> layers = repository.getTestCaseLayers();

        assertEquals(1, layers.size());
        assertEquals(layer, layers.get(0));
    }

    @Test
    void getTestCaseAutomation_success() {
        Automation automation = new Automation("Automated", UUID.randomUUID());

        when(jdbcOperations.query(
                eq("SELECT automation_status_id,automation_name from automation_status"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<Automation>>any()
        )).thenReturn(List.of(automation));

        List<Automation> automations = repository.getTestCaseAutomation();

        assertEquals(1, automations.size());
        assertEquals(automation, automations.get(0));
    }

    @Test
    void addTestSteps_success() {
        StepsRequest stepRequest = new StepsRequest("desc", "data", "result", 1);
        UUID testCaseId = UUID.randomUUID();

        when(jdbcOperations.update(
                eq("INSERT INTO test_step(step_description,test_case_id,step_data,step_result,step_number) VALUES(?,?,?,?,?)"),
                eq(stepRequest.getStepDescription()),
                eq(testCaseId),
                eq(stepRequest.getStepData()),
                eq(stepRequest.getStepResult()),
                eq(stepRequest.getStepNumber())
        )).thenReturn(1);

        // void method — просто проверим, что не выбрасывает исключений
        assertDoesNotThrow(() -> repository.addTestSteps(stepRequest, testCaseId));
    }
}
