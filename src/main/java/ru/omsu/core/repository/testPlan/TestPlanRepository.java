package ru.omsu.core.repository.testPlan;

import org.springframework.jdbc.core.JdbcOperations;
import ru.omsu.core.model.TestPlan;
import ru.omsu.core.model.TestPlanDTO;
import ru.omsu.web.model.request.AddTestPlanRequest;

import java.util.List;
import java.util.UUID;

/**
 * class test plan for repository
 */
public class TestPlanRepository implements ITestPlanRepository {
    private final JdbcOperations jdbcOperations;

    /**
     * @param jdbcOperations for request to db
     */
    public TestPlanRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public UUID addTestPlan(AddTestPlanRequest addTestPlanRequest) {
        return jdbcOperations.queryForObject("INSERT INTO test_plan(test_plan_name) VALUES (?) RETURNING test_plan_id",
                (resultSet, i) -> UUID.fromString(resultSet.getString("test_plan_id")),
                addTestPlanRequest.testPlanName());
    }

    @Override
    public void addTestCasesInTestPlan(UUID testCaseId, UUID testPlanId) {
        jdbcOperations.update("INSERT INTO test_cases_in_test_plan(test_plan_id,test_case_id) values (?,?)", testPlanId,
                testCaseId);
    }

    @Override
    public List<UUID> getTestPlansIdInProject(UUID projectId, int limit, int offset) {
        return jdbcOperations.query("SELECT test_plan_id from test_plans_in_project where project_id=?  limit ? offset ((?-1)*10)",
                (resultSet, i) -> (UUID.fromString(resultSet.getString("test_plan_id"))), projectId, limit, offset);
    }

    @Override
    public TestPlanDTO getTestPlane(UUID testPlanId) {
        return jdbcOperations.queryForObject("SELECT test_plan_name from test_plan where test_plan_id=?",
                (resultSet, i) -> (new TestPlanDTO(testPlanId, resultSet.getString("test_plan_name"))));
    }

    @Override
    public void addTestPlanForProject(UUID testPlanId, UUID projectId) {
        jdbcOperations.update("INSERT INTO test_plans_in_project(test_plan_id,project_id) values (?,?)",
                testPlanId, projectId);
    }
}
