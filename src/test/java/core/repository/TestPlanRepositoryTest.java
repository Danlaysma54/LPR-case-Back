package core.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import ru.omsu.core.model.*;
import ru.omsu.core.repository.testPlan.TestPlanRepository;
import ru.omsu.web.model.exception.IdNotExist;
import ru.omsu.web.model.request.EditTestPlanRequest;
import ru.omsu.web.model.request.TestPlanRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestPlanRepositoryTest {

    private JdbcOperations jdbcOperations;
    private TestPlanRepository repository;

    @BeforeEach
    void setUp() {
        jdbcOperations = mock(JdbcOperations.class);
        repository = new TestPlanRepository(jdbcOperations);
    }

    @Test
    void addTestPlan_success() {
        TestPlanRequest request = new TestPlanRequest("Plan 1",new ArrayList<>());
        UUID expectedId = UUID.randomUUID();

        when(jdbcOperations.queryForObject(
                eq("INSERT INTO test_plan(test_plan_name) values(?) RETURNING test_plan_id"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<UUID>>any(),
                eq(request.getTestPlanName())
        )).thenReturn(expectedId);

        UUID actualId = repository.addTestPlan(request);

        assertEquals(expectedId, actualId);
    }

    @Test
    void addTestCaseInTestPlan_success() {
        UUID testCaseId = UUID.randomUUID();
        UUID testPlanId = UUID.randomUUID();

        when(jdbcOperations.update(
                eq("INSERT INTO test_cases_in_test_plan(test_plan_id,test_case_id) values(?,?)"),
                eq(testPlanId),
                eq(testCaseId)
        )).thenReturn(1);

        // void method — проверяем, что не выбрасывает исключений
        assertDoesNotThrow(() -> repository.addTestCaseInTestPlan(testCaseId, testPlanId));
    }

    @Test
    void getTestPlans_success() {
        TestPlanDTO dto = new TestPlanDTO(UUID.randomUUID(), "Plan 1");

        when(jdbcOperations.query(
                eq("SELECT test_plan_id,test_plan_name from test_plan"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<TestPlanDTO>>any()
        )).thenReturn(List.of(dto));

        List<TestPlanDTO> result = repository.getTestPlans(UUID.randomUUID());

        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
    }

    @Test
    void getCaseInTestPlans_success() {
        CaseDTO dto = new CaseDTO("Case 1", UUID.randomUUID());
        UUID testPlanId = UUID.randomUUID();

        when(jdbcOperations.query(
                contains("select test_case.test_case_id,test_case_name from test_case"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<CaseDTO>>any(),
                eq(testPlanId)
        )).thenReturn(List.of(dto));

        List<CaseDTO> result = repository.getCaseInTestPlans(testPlanId);

        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
    }

    @Test
    void getCaseWithSuitesInTestPlans_success() {
        CaseForPlanDTO dto = new CaseForPlanDTO("Case 1", UUID.randomUUID(), UUID.randomUUID());
        UUID testPlanId = UUID.randomUUID();

        when(jdbcOperations.query(
                contains("select test_case.test_case_id,test_case_name, suite_id from test_case"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<CaseForPlanDTO>>any(),
                eq(testPlanId)
        )).thenReturn(List.of(dto));

        List<CaseForPlanDTO> result = repository.getCaseWithSuitesInTestPlans(testPlanId);

        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
    }

    @Test
    void deleteTestPlan_success() {
        UUID testPlanId = UUID.randomUUID();

        when(jdbcOperations.update(
                eq("DELETE FROM test_plan where test_plan_id=?"),
                eq(testPlanId)
        )).thenReturn(1);

        assertDoesNotThrow(() -> repository.deleteTestPlan(testPlanId));
    }

    @Test
    void editTestPlanName_success() {
        EditTestPlanRequest request = new EditTestPlanRequest(UUID.randomUUID(), "New Plan Name",new ArrayList<>());

        when(jdbcOperations.update(
                eq("UPDATE test_plan set test_plan_name=? where test_plan_id=?"),
                eq(request.getTestPlanName()),
                eq(request.getTestPlanId())
        )).thenReturn(1);

        assertDoesNotThrow(() -> repository.editTestPlanName(request));
    }

    @Test
    void deleteAllTestCasesInTestPlan_success() {
        UUID testPlanId = UUID.randomUUID();

        when(jdbcOperations.update(
                eq("delete from test_cases_in_test_plan where test_plan_id=?"),
                eq(testPlanId)
        )).thenReturn(1);

        assertDoesNotThrow(() -> repository.deleteAllTestCasesInTestPlan(testPlanId));
    }

    @Test
    void getTestPlanById_success() {
        UUID testPlanId = UUID.randomUUID();
        UUID projectId = UUID.randomUUID();
        List<CaseForPlanDTO> cases = List.of(new CaseForPlanDTO("Case", UUID.randomUUID(), UUID.randomUUID()));
        TestPlanWithSuitesId expected = new TestPlanWithSuitesId(testPlanId, "Plan name", cases);

        when(repository.getCaseWithSuitesInTestPlans(testPlanId)).thenReturn(cases);

        when(jdbcOperations.queryForObject(
                eq("SELECT test_plan_id,test_plan_name from test_plan where test_plan_id=?"),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<TestPlanWithSuitesId>>any(),
                eq(testPlanId)
        )).thenReturn(expected);

        TestPlanWithSuitesId actual = repository.getTestPlanById( testPlanId);

        assertEquals(expected, actual);
    }

    @Test
    void getTestPlanById_notFound() {
        UUID testPlanId = UUID.randomUUID();
        UUID projectId = UUID.randomUUID();

        when(repository.getCaseWithSuitesInTestPlans(testPlanId)).thenReturn(List.of());

        when(jdbcOperations.queryForObject(
                anyString(),
                ArgumentMatchers.<org.springframework.jdbc.core.RowMapper<TestPlanWithSuitesId>>any(),
                eq(testPlanId)
        )).thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(IdNotExist.class, () -> repository.getTestPlanById( testPlanId));
    }

}
