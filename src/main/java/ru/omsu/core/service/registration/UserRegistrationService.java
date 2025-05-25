    package ru.omsu.core.service.registration;

    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;
    import ru.omsu.core.model.User;
    import ru.omsu.core.repository.user.IUserRepository;
    import ru.omsu.core.repository.user.UserRepository;
    import ru.omsu.web.model.request.RegistrationRequestDto;

    import java.util.UUID;

    @Service
    public class UserRegistrationService {

        private final IUserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }

        public void registerUser(RegistrationRequestDto request) {
            if (userRepository.existsByUsername(request.username())) {
                throw new IllegalArgumentException("User with this username already exist");
            }

            User user = new User();
            user.setUsername(request.username());
            user.setPassword(passwordEncoder.encode(request.password()));

            UUID userId = userRepository.save(user);
        }
    }
