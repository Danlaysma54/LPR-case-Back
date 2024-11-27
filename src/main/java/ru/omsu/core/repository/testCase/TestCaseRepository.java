package ru.omsu.core.repository.testCase;

import ru.omsu.core.model.TestCase;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TestCaseRepository implements ITestCaseRepository {
    private final JdbcOperations jdbcOperations;

    public TestCaseRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public UUID addTestCase(TestCase testCase) {
        UUID newID = UUID.randomUUID();
        jdbcOperations.update("INSERT INTO test_case(test_case_id,test_case_name,suite_id,automation_status,layer) VALUES (?,?,?,?,?)",
                newID, testCase.getTestCaseName(), testCase.getSuiteId(), testCase.getIsAutomated(), testCase.getLayer());
        return newID;
    }

    @Override
    public TestCase getTestCase(UUID testCaseId) {
        return jdbcOperations.queryForObject("SELECT test_case_id,test_case_name,suite_id,automation_status,layer from test_case where test_case_id=?",
                (resultSet, i) -> new TestCase(
                        UUID.fromString(resultSet.getString("test_case_id")),
                        resultSet.getString("test_case_name"),
                        resultSet.getString("layer"), resultSet.getString("automation_status"),
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