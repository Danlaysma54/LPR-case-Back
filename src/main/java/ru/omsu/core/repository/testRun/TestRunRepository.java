package ru.omsu.core.repository.testRun;

import org.springframework.jdbc.core.JdbcOperations;
import ru.omsu.core.model.TestRun;

import java.util.List;

public class TestRunRepository implements ITestRunRepository {
    private final JdbcOperations jdbcOperations;

    public TestRunRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<TestRun> getTestRun() {
        jdbcOperations.query("Select test_name,test_run_id from ")
    }
}
