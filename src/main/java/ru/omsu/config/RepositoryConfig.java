package ru.omsu.config;

import ru.omsu.core.repository.testCase.TestCaseRepository;
import ru.omsu.core.repository.tree.TreeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;

@Configuration
public class RepositoryConfig {
    @Bean
    public TreeRepository TreeRepository(@Qualifier("lprJdbcOperations") final JdbcOperations jdbcOperations) {
        return new TreeRepository(jdbcOperations);
    }

    @Bean
    public TestCaseRepository TestCaseRepository(@Qualifier("lprJdbcOperations") final JdbcOperations jdbcOperations) {
        return new TestCaseRepository(jdbcOperations);
    }

}
