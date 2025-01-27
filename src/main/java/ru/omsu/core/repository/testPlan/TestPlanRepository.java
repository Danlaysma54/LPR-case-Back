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
        return jdbcOperations.queryForObject()
    }
}
