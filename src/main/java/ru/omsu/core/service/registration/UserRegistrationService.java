package ru.omsu.core.service.registration;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.omsu.core.model.User;
import ru.omsu.core.repository.user.UserRepository;
import ru.omsu.web.model.request.RegistrationRequestDto;

@Service
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegistrationRequestDto request) {
        if (userRepository.existsByUsername(request.username())){
            throw new IllegalArgumentException();
        }

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));

        return userRepository.save(user);
    }
}
