package ru.omsu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import ru.omsu.core.repository.project.ProjectRepository;
import ru.omsu.web.filter.UserInProjectFilter;

public class FilterConfiguration {
    @Bean
    UserInProjectFilter userInProjectFilter(JwtDecoder jwtDecoder, ProjectRepository projectRepository){
        return new UserInProjectFilter(jwtDecoder,projectRepository);
    }
}
