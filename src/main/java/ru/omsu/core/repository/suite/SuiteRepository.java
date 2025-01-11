package ru.omsu.core.repository.suite;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.omsu.core.model.Suite;
import ru.omsu.core.model.SuiteDTO;
import ru.omsu.core.model.TestCase;
import ru.omsu.core.repository.tree.TreeRepository;
import ru.omsu.web.model.request.TestCaseRequest;

import java.util.UUID;

@Repository
public class SuiteRepository implements ISuiteRepository {
    private final JdbcOperations jdbcOperations;

    public SuiteRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public SuiteDTO getSuite(UUID suiteId) {
        try {
            return jdbcOperations.queryForObject("select * from suite where suite_id=?",
                    (resultSet, i) -> new SuiteDTO(
                            UUID.fromString(resultSet.getString("suite_id")),
                            resultSet.getString("suite_name"),
                            UUID.fromString(resultSet.getString("suite_root_id"))),
                    suiteId);
        } catch (EmptyResultDataAccessException ignored) {}
        return null;
    }
}
