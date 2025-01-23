package ru.omsu.core.repository.tree;

import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.Suite;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public class TreeRepository implements ITreeRepository {
    private final JdbcOperations jdbcOperations;

    public TreeRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public List<Suite> getOneLevelSuites(UUID suiteID, int offset, int limit) {
        int skipsRow = limit * offset;
        return jdbcOperations.query("SELECT suite_id,suite_name,suite_root_id " +
                "from suite where suite_root_id = CAST(? AS UUID)", (resultSet, i) -> {
            UUID suiteId = resultSet.getObject("suite_id", UUID.class);
            UUID suiteRootId = resultSet.getObject("suite_root_id", UUID.class);
            String suiteName = resultSet.getString("suite_name");
            return new Suite(suiteName, suiteId, suiteRootId);
        }, suiteID.toString());
    }

    @Override
    public List<CaseDTO> getOneLevelCases(UUID suiteID, int offset, int limit) {
        return jdbcOperations.query("SELECT test_case_id,test_case_name from test_case where suite_id = CAST(? AS UUID)", (resultSet, i) -> {
            UUID testCaseId = resultSet.getObject("test_case_id", UUID.class);
            String testCaseName = resultSet.getString("test_case_name");
            return new CaseDTO(testCaseName, testCaseId);
        }, suiteID.toString());
    }

    @Override
    public List<Suite> getAllSuites(UUID projectID) {
        String sql = """
                WITH RECURSIVE suite_hierarchy AS (
                    SELECT suite_id, suite_name, suite_root_id
                    FROM suite
                    WHERE suite_root_id = CAST(? AS UUID)

                    UNION ALL
                    
                    SELECT s.suite_id, s.suite_name, s.suite_root_id
                    FROM suite AS s
                    INNER JOIN suite_hierarchy AS sh ON s.suite_root_id = sh.suite_id
                )
                SELECT 
                    suite_id,
                    suite_name,
                    suite_root_id,
                    (SELECT COUNT(*) 
                     FROM suite AS inner_suite 
                     WHERE inner_suite.suite_root_id = sh.suite_id) AS suite_child_count
                FROM suite_hierarchy AS sh;
                """;

        return jdbcOperations.query(sql, (resultSet, i) -> {
            UUID suiteId = resultSet.getObject("suite_id", UUID.class);
            UUID suiteRootId = resultSet.getObject("suite_root_id", UUID.class);
            String suiteName = resultSet.getString("suite_name");
            return new Suite(suiteName, suiteId, suiteRootId);
        }, projectID.toString());
    }

}