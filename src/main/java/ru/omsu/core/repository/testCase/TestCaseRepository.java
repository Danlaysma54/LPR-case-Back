package ru.omsu.core.repository.testCase;

import ru.omsu.core.model.Step;
import ru.omsu.core.model.TestCase;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.omsu.web.model.request.TestCaseRequest;

import java.util.List;
import java.util.UUID;

/**
 * class implementation of test case Repository by postgres
 */
@Repository
public class TestCaseRepository implements ITestCaseRepository {
    private final JdbcOperations jdbcOperations;

    /**
     * @param jdbcOperations jdbc
     */
    public TestCaseRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    /**
     * @param testCaseRequest object for request to add test case
     * @return UUID of added test case
     */
    @Override
    public UUID addTestCase(final TestCaseRequest testCaseRequest) {
        return jdbcOperations.queryForObject("INSERT INTO test_case(test_case_name,suite_id,automation_status_id,layer_id) VALUES (?,?,?,?) RETURNING test_case_id",
                (resultSet, i) -> UUID.fromString(resultSet.getString("test_case_id")),
                testCaseRequest.testCaseName(), testCaseRequest.suiteId(),
                testCaseRequest.isAutomatedId(), testCaseRequest.layerId());
    }

    /**
     * @param testCaseId id of test case
     * @return test case entity
     */
    @Override
    public TestCase getTestCase(final UUID testCaseId) {
        return jdbcOperations.queryForObject("select test_case_id,test_case_name,suite_id,layer_name,automation_name from test_case" +
                        " inner join layer on layer.layer_id=test_case.layer_id " +
                        "inner join automation_status on automation_status.automation_status_id=test_case.automation_status_id" +
                        " where test_case_id=?",
                (resultSet, i) -> new TestCase(
                        UUID.fromString(resultSet.getString("test_case_id")),
                        resultSet.getString("test_case_name"),
                        resultSet.getString("layer_name"),
                        resultSet.getString("automation_name"),
                        UUID.fromString(resultSet.getString("suite_id"))),
                testCaseId);
    }

    /**
     * @param testCaseId id of test case
     */
    @Override
    public void deleteTestCase(final UUID testCaseId) {
        if (jdbcOperations.update("DELETE FROM test_case where test_case_id = CAST(? AS UUID)", testCaseId.toString()) < 1) {
            throw new IllegalArgumentException("Test Case with this ID doesn't exist");
        }
    }

    /**
     * @param testCase entity of test case
     */
    @Override
    public void editTestCase(final TestCase testCase) {
        if (jdbcOperations.update("UPDATE test_case SET test_case_name=?,suite_id=?,automation_status=?,layer=? where test_case_id=?",
                testCase.testCaseName(), testCase.suiteId(), testCase.isAutomated(), testCase.layer(), testCase.testCaseId()) < 1) {
            throw new IllegalArgumentException("Test Case with that Id doesn't exist");
        }
    }

    /**
     *
     * @param testCaseId id of test case
     * @return list of cases steps
     */
    @Override
    public List<Step> getTestCaseSteps(final UUID testCaseId) {
        return jdbcOperations.query("SELECT step_description,step_data,step_result,step_number from test_step where test_case_id=?",
                (resultSet, i) -> new Step(resultSet.getString("step_description"), resultSet.getString("step_data"),
                        resultSet.getString("step_result"), resultSet.getInt("step_number"))
            , testCaseId);
    }
}
