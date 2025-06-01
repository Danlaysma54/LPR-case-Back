package ru.omsu.core.service.authentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.omsu.core.repository.user.UserRepository;

public class JDBCUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JDBCUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new CustomUserDetails(
                        user.getUsername(),
                        user.getPassword(),
                        user.getUserID().toString()  // предполагаем, что у User есть getId()
                ))
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User with username [%s] not found".formatted(username)
                ));
    }

}
