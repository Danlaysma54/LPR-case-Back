package ru.omsu.core.repository.suite;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.omsu.core.model.Suite;
import ru.omsu.web.model.request.AddSuiteRequest;

import java.util.UUID;

@Repository
public class SuiteRepository implements ISuiteRepository {
    private final JdbcOperations jdbcOperations;

    public SuiteRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public Suite getSuite(UUID suiteId) {
        try {
            return jdbcOperations.queryForObject("select * from suite where suite_id=?",
                    (resultSet, i) -> new Suite(
                            resultSet.getString("suite_name"),
                            UUID.fromString(resultSet.getString("suite_id")),
                            UUID.fromString(resultSet.getString("suite_root_id"))),
                    suiteId);
        } catch (EmptyResultDataAccessException ignored) {
        }
        return null;
    }

    @Override
    public UUID addSuite(AddSuiteRequest addSuiteRequest) {
        return jdbcOperations.queryForObject("INSERT INTO suite(suite_name,suite_root_id) VALUES (?,?) RETURNING suite_id",
                (resultSet, i) -> UUID.fromString(resultSet.getString("suite_id")),
                addSuiteRequest.getSuiteName(),addSuiteRequest.getSuiteRootId());
    }
}
