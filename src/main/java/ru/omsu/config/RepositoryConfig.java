package ru.omsu.config;

import org.springframework.context.annotation.Primary;
import ru.omsu.core.repository.suite.SuiteRepository;
import ru.omsu.core.repository.testCase.TestCaseRepository;
import ru.omsu.core.repository.testPlan.TestPlanRepository;
import ru.omsu.core.repository.tree.TreeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import ru.omsu.core.repository.user.UserRepository;

/**
 * class for repository configuration
 */
@Configuration
public class RepositoryConfig {
    /**
     * @param jdbcOperations for creating repo class
     * @return tree Repository object
     */
    @Primary
    @Bean
    public TreeRepository treeRepository(@Qualifier("lprJdbcOperations") final JdbcOperations jdbcOperations) {
        return new TreeRepository(jdbcOperations);
    }

    /**
     * @param jdbcOperations for creating repo class
     * @return test case Repository object
     */
    @Bean
    public TestCaseRepository testCaseRepository(@Qualifier("lprJdbcOperations") final JdbcOperations jdbcOperations) {
        return new TestCaseRepository(jdbcOperations);
    }

    /**
     * @param jdbcOperations for creating repo class
     * @return suite Repository object
     */
    @Bean
    public SuiteRepository suiteRepository(@Qualifier("lprJdbcOperations") final JdbcOperations jdbcOperations) {
        return new SuiteRepository(jdbcOperations);
    }

    @Bean
    public TestPlanRepository TestPLanRepository(@Qualifier("lprJdbcOperations") final JdbcOperations jdbcOperations) {
        return new TestPlanRepository(jdbcOperations);
    }

    @Bean
    public UserRepository userRepository(@Qualifier("lprJdbcOperations") final JdbcOperations jdbcOperations) {
        return new UserRepository(jdbcOperations);
    }
}
