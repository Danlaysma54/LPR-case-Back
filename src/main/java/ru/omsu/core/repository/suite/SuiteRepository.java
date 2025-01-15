package ru.omsu.core.repository.suite;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.omsu.core.model.Suite;
import ru.omsu.web.model.exception.IdNotExist;
import ru.omsu.web.model.request.AddSuiteRequest;

import java.util.UUID;

/**
 * class implementation of Suite Repository by postgres
 */
@Repository
public class SuiteRepository implements ISuiteRepository {
    private final JdbcOperations jdbcOperations;

    /**
     * @param jdbcOperations jdbc
     */
    public SuiteRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    /**
     * @param suiteId id of suite
     * @return Suite entity
     */
    @Override
    public Suite getSuite(final UUID suiteId) {
        try {
            return jdbcOperations.queryForObject("select suite_name,suite_id,suite_root_id from suite where suite_id=?",
                    (resultSet, i) -> new Suite(
                            resultSet.getString("suite_name"),
                            UUID.fromString(resultSet.getString("suite_id")),
                            UUID.fromString(resultSet.getString("suite_root_id"))),
                    suiteId);
        } catch (EmptyResultDataAccessException ignored) {
            throw new IdNotExist("Suite with that id doesn't exist");
        }
    }

    /**
     * @param addSuiteRequest request entity to add suite
     * @return UUID of added suite
     */
    @Override
    public UUID addSuite(final AddSuiteRequest addSuiteRequest) {
        return jdbcOperations.queryForObject("INSERT INTO suite(suite_name,suite_root_id) VALUES (?,?) RETURNING suite_id",
                (resultSet, i) -> UUID.fromString(resultSet.getString("suite_id")),
                addSuiteRequest.getSuiteName(), addSuiteRequest.getSuiteRootId());
    }

    @Override
    public void editSuite(final Suite suite) {
        if (jdbcOperations.update("UPDATE suite set suite_name = ?, suite_root_id = ? where suite_id = ?",
                suite.getSuiteId(), suite.getSuiteRootId(), suite.getSuiteId()) < 1) {
            throw new IdNotExist("Suite with that id doesn't exist");
        }
    }

    @Override
    public void deleteSuite(final UUID suiteId) {
        if (jdbcOperations.update("DELETE from suite where suite_id=?",
                suiteId) < 1) {
            throw new IdNotExist("Suite with that id doesn't exist");
        }
    }
}
