package ru.omsu.core.repository.testPlan;

import org.springframework.jdbc.core.JdbcOperations;
import ru.omsu.web.model.request.AddTestPlanRequest;

import java.util.UUID;

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
                addTestPlanRequest.testPlanName(), addTestPlanRequest.testPlanName());
    }

    @Override
    public void addTestCasesInTestPlan(UUID testCaseId, UUID testPlanId) {
        jdbcOperations.update("INSERT INTO test_cases_in_test_plan(test_plan_id,test_case_id values (?,?)", testPlanId, testCaseId);
    }

}
