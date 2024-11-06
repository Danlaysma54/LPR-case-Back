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
        jdbcOperations.update("INSERT INTO test_case(test_case_id,test_case_name,suite_id,automation_status,layer) VALUES (?,?,?,?)",
                newID, testCase.getTestCaseName(), testCase.getSuiteId(), testCase.getIsAutomated(), testCase.getLayer());
        return newID;
    }

    @Override
    public TestCase getTestCase(UUID testCaseId) {
       return jdbcOperations.queryForObject("SELECT test_case_id,test_case_name,suite_id,automation_status,layer from test_case where test_case_id=?",
                (resultSet, i) -> new TestCase(
                        UUID.fromString(resultSet.getString("test_case_id")),
                        resultSet.getString("test_case_name"),
                        resultSet.getString("layer"),resultSet.getString("automation_status"),
                        UUID.fromString(resultSet.getString("suite_id"))),
               testCaseId);
    }

}
