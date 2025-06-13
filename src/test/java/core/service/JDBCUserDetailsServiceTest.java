package core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.omsu.core.model.User;
import ru.omsu.core.repository.user.UserRepository;
import ru.omsu.core.service.authentication.JDBCUserDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JDBCUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JDBCUserDetailsService userDetailsService;

    private final String TEST_USERNAME = "testuser";
    private final String TEST_PASSWORD = "testpass";
    private final String NOT_FOUND_USERNAME = "nonexistent";

    @Test
    void loadUserByUsername_ShouldThrow_WhenUserNotFound() {
        // Arrange
        when(userRepository.findByUsername(NOT_FOUND_USERNAME)).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(NOT_FOUND_USERNAME);
        });

        assertEquals("User with username [nonexistent] not found", exception.getMessage());
        verify(userRepository).findByUsername(NOT_FOUND_USERNAME);
    }

    @Test
    void loadUserByUsername_ShouldThrow_WhenUsernameIsNull() {
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(null);
        });
    }

    @Test
    void loadUserByUsername_ShouldThrow_WhenUsernameIsEmpty() {
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("");
        });
    }

    @Test
    void loadUserByUsername_ShouldThrow_WhenUsernameIsBlank() {
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("   ");
        });
    }
}