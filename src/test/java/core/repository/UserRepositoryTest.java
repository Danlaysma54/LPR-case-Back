package core.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import ru.omsu.core.model.User;
import ru.omsu.core.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

    private JdbcOperations jdbcOperations;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        jdbcOperations = mock(JdbcOperations.class);
        userRepository = new UserRepository(jdbcOperations);
    }

    @Test
    void testExistsByUsername_true() {
        when(jdbcOperations.query(anyString(), any(org.springframework.jdbc.core.RowMapper.class), eq("john")))
                .thenReturn(List.of("some-user-id"));

        boolean result = userRepository.existsByUsername("john");

        assertTrue(result);
    }

    @Test
    void testExistsByUsername_false() {
        when(jdbcOperations.query(anyString(), any(org.springframework.jdbc.core.RowMapper.class), eq("john")))
                .thenReturn(List.of());

        boolean result = userRepository.existsByUsername("john");

        assertFalse(result);
    }

    @Test
    void testSave_success() {
        UUID expectedId = UUID.randomUUID();
        User user = new User(expectedId, "john", "password");

        when(jdbcOperations.queryForObject(anyString(), any(org.springframework.jdbc.core.RowMapper.class), eq("john"), eq("password")))
                .thenReturn(expectedId);

        UUID result = userRepository.save(user);

        assertEquals(expectedId, result);
    }

    @Test
    void testGetUser_success() {
        UUID userId = UUID.randomUUID();
        User expectedUser = new User(userId, "john", "password");

        when(jdbcOperations.queryForObject(anyString(), any(org.springframework.jdbc.core.RowMapper.class), eq(userId)))
                .thenReturn(expectedUser);

        User result = userRepository.getUser(userId);

        assertEquals(expectedUser.getUserID(), result.getUserID());
        assertEquals(expectedUser.getUsername(), result.getUsername());
        assertEquals(expectedUser.getPassword(), result.getPassword());
    }

    @Test
    void testFindByUsername_success() {
        UUID userId = UUID.randomUUID();
        User expectedUser = new User(userId, "john", "password");

        when(jdbcOperations.queryForObject(anyString(), any(org.springframework.jdbc.core.RowMapper.class), eq("john")))
                .thenReturn(expectedUser);

        Optional<User> result = userRepository.findByUsername("john");

        assertTrue(result.isPresent());
        assertEquals(expectedUser.getUsername(), result.get().getUsername());
    }

}
