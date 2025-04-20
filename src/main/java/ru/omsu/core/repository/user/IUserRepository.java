package ru.omsu.core.repository.user;

import ru.omsu.core.model.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    boolean existsByUsername(String email);

    UUID save(User user);

    User getUser(UUID userID);

    Optional<User> findByUsername(String username);
}
