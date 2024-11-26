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
        return jdbcOperations.queryForObject("INSERT INTO test_case(test_case_id,test_case_name,suite_id,automation_status_id,layer_id) VALUES (?,?,?,?,?) RETURNING test_case_id",
                (resultSet, i) -> UUID.fromString(resultSet.getString("test_case_id")),
                testCaseRequest.getTestCaseName(), testCaseRequest.getSuiteId(), testCaseRequest.getIsAutomatedId(), testCaseRequest.getLayerId());
    }

    @Override
    public TestCase getTestCase(UUID testCaseId) {
        return jdbcOperations.queryForObject("SELECT test_case_id,test_case_name,suite_id,automation_status_name,layer_name from test_case where test_case_id=? " +
                        "LEFT JOIN layer ON layer.layer_id =test_case.layer_id " +
                        "LEFT JOIN automation_status ON automation_status.automation_status_id=test_case.automation_status_id",
                (resultSet, i) -> new TestCase(
                        UUID.fromString(resultSet.getString("test_case_id")),
                        resultSet.getString("test_case_name"),
                        resultSet.getString("layer"), resultSet.getString("automation_status"),
                        UUID.fromString(resultSet.getString("suite_id"))),
                testCaseId);
    }

}
