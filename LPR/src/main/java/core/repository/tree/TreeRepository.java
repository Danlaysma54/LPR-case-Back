package core.repository.tree;

import core.model.CaseAndSuite;
import org.springframework.jdbc.core.JdbcOperations;


import java.util.List;
import java.util.UUID;

public class TreeRepository implements ITreeRepository {
    private JdbcOperations jdbcOperations;

    public TreeRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public List<CaseAndSuite> getFirstLevelSuites(UUID projectID) {
        return jdbcOperations.query("SELECT suite_id,suite_name from suite where suite_root_id = ?", (resultSet, i) -> {
            UUID suiteId = resultSet.getObject("suite_id", UUID.class);
            String suiteName = resultSet.getString("suite_name");
            return new CaseAndSuite(suiteId, projectID, suiteName, false);
        },projectID.toString());
    }

    @Override
    public List<CaseAndSuite> getFirstLevelCases(UUID projectID) {
        return jdbcOperations.query("SELECT test_case_id,case_name from suite where suite_id = ?", (resultSet, i) -> {
            UUID testCaseId = resultSet.getObject("test_case_id", UUID.class);
            String testCaseName = resultSet.getString("test_case_name");
            return new CaseAndSuite(testCaseId, projectID, testCaseName, false);
        },projectID.toString());
    }
}