package ru.omsu.core.repository.user;

import org.springframework.jdbc.core.JdbcOperations;
import ru.omsu.core.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepository implements IUserRepository {
    private final JdbcOperations jdbcOperations;

    public UserRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public boolean existsByUsername(String username) {
        List<String> res = jdbcOperations.query("SELECT userID from users where username=?",
                (resultset, i) -> resultset.getString("userID"), username);
        return !res.isEmpty();
    }

    @Override
    public UUID save(User user) {
        return jdbcOperations.queryForObject("INSERT INTO users (username,password) VALUES (?,?) RETURNING userID",
                (resultSet, i) -> UUID.fromString(resultSet.getString("userID")),
                user.getUsername(), user.getPassword());
    }

    @Override
    public User getUser(UUID userID) {
        return jdbcOperations.queryForObject("select userID,username,password from users where userID=?",
                (resultSet, i) -> new User(
                        UUID.fromString(resultSet.getString("userID")),
                        resultSet.getString("userName"),
                        resultSet.getString("password")
                ), userID);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(jdbcOperations.queryForObject("select userID,username,password from users where username=?",
                (resultSet, i) -> new User(
                        UUID.fromString(resultSet.getString("userID")),
                        resultSet.getString("userName"),
                        resultSet.getString("password")
                ), username));
    }
}
