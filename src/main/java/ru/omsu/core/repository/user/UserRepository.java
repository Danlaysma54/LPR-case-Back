package ru.omsu.core.repository.user;

import org.springframework.jdbc.core.JdbcOperations;
import ru.omsu.core.model.User;

import java.util.UUID;

public class UserRepository implements IUserRepository {
    private final JdbcOperations jdbcOperations;

    public UserRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public boolean existsByUsername(String username) {
        int exists = Integer.getInteger(jdbcOperations.queryForObject("SELECT count(userID) as numb from users where username=?",
                (resultset, i) -> resultset.getString("numb")));
        return exists > 0;
    }

    @Override
    public void save(User user) {
        return jdbcOperations.queryForObject("INSERT INTO users (test_case_name,suite_id,automation_status_id,layer_id) VALUES (?,?,?,?) RETURNING test_case_id",
                (resultSet, i) -> UUID.fromString(resultSet.getString("test_case_id")),
                testCaseRequest.testCaseName(), testCaseRequest.suiteId(),
                testCaseRequest.isAutomatedId(), testCaseRequest.layerId());
    }

    @Override
    public User getUser(UUID userID) {
        return null;
    }
}
