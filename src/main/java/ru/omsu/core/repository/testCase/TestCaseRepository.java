package ru.omsu.core.repository.testCase;

import ru.omsu.core.model.TestCase;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.omsu.web.model.request.TestCaseRequest;

import java.util.UUID;

@Repository
public class TestCaseRepository implements ITestCaseRepository {
    private final JdbcOperations jdbcOperations;

    public TestCaseRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public UUID addTestCase(TestCaseRequest testCaseRequest) {
        return jdbcOperations.queryForObject("INSERT INTO test_case(test_case_name,suite_id,automation_status_id,layer_id) VALUES (?,?,?,?) RETURNING test_case_id",
                (resultSet, i) -> UUID.fromString(resultSet.getString("test_case_id")),
                testCaseRequest.getTestCaseName(), testCaseRequest.getSuiteId(), testCaseRequest.getIsAutomatedId(), testCaseRequest.getLayerId());
    }

    @Override
    public TestCase getTestCase(UUID testCaseId) {
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

    @Override
    public void deleteTestCase(UUID testCaseId) {
        if (jdbcOperations.update("DELETE FROM test_case where test_case_id = CAST(? AS UUID)", testCaseId.toString()) < 1) {
            throw new IllegalArgumentException("Test Case with this ID doesn't exist");
        }
    }

    @Override
    public void editTestCase(TestCase testCase) {
        if (jdbcOperations.update("UPDATE test_case SET test_case_name=?,suite_id=?,automation_status=?,layer=? where test_case_id=?",
                testCase.getTestCaseName(), testCase.getSuiteId(), testCase.getIsAutomated(), testCase.getLayer(), testCase.getTestCaseId()) < 1) {
            throw new IllegalArgumentException("Test Case with that Id doesn't exist");
        }
    }
}
