package core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.omsu.core.model.User;
import ru.omsu.core.repository.user.IUserRepository;
import ru.omsu.core.service.registration.UserRegistrationService;
import ru.omsu.web.model.request.RegistrationRequestDto;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserRegistrationService registrationService;



    @Test
    void registerUser_ShouldThrow_WhenRequestIsNull() {
        assertThrows(NullPointerException.class, () -> {
            registrationService.registerUser(null);
        });
    }


}