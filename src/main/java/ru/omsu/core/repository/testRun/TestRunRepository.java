package ru.omsu.core.repository.testRun;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.omsu.core.model.TestRun;
import ru.omsu.core.model.TestRunDTO;
import ru.omsu.web.model.request.AddTestRunRequest;

import java.util.List;
import java.util.UUID;

@Repository
public class TestRunRepository implements ITestRunRepository {
    private final JdbcOperations jdbcOperations;

    public TestRunRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<TestRunDTO> getTestRun() {
        return jdbcOperations.query("Select test_name,test_run_id from test_run", (resultSet, i) ->
                new TestRunDTO(UUID.fromString(resultSet.getString("test_run_id")), resultSet.getString("test_name")));
    }

    @Override
    public UUID addTestRun(final AddTestRunRequest addTestRunRequest) {
        return jdbcOperations.queryForObject("insert into test_run(test_name,test_plan_id) values(?,?) returning test_run_id",
                (resultSet, i) -> UUID.fromString(resultSet.getString("test_run_id")),
                addTestRunRequest.testRunName(), addTestRunRequest.testPlanId());
    }

    @Override
    public void editTestRun(final TestRun testRun) {
        jdbcOperations.update("update test_run set test_name=?,test_plan_id=? where test_run_id=?",
                testRun.testName(), testRun.testPlanId(), testRun.testRunId());
    }

    @Override
    public TestRun getTestRunById(UUID testRunId) {
        return jdbcOperations.queryForObject("select test_name,test_plan_id from test_run where test_run_id=?",
                (resultSet, i) -> new TestRun(testRunId,
                        UUID.fromString(resultSet.getString("test_plan_id")),
                        resultSet.getString("test_name")),testRunId);
    }

    @Override
    public void deleteTestRun(UUID testRunId) {
        jdbcOperations.update("delete from test_run where test_run_id=?", testRunId);
    }
}
