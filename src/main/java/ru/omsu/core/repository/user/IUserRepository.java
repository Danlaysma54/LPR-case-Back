package ru.omsu.core.repository.user;

import ru.omsu.core.model.User;

import java.util.UUID;

public interface IUserRepository {
    boolean existsByUsername(String email);

    void save(User user);

    User getUser(UUID userID);
}
