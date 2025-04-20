package ru.omsu.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.omsu.core.repository.suite.ISuiteRepository;
import ru.omsu.core.repository.testCase.ITestCaseRepository;
import ru.omsu.core.repository.testPlan.ITestPlanRepository;
import ru.omsu.core.repository.testPlan.TestPlanRepository;
import ru.omsu.core.repository.tree.ITreeRepository;
import ru.omsu.core.repository.user.IUserRepository;
import ru.omsu.core.repository.user.UserRepository;
import ru.omsu.core.service.authentication.AuthenticationService;
import ru.omsu.core.service.authentication.JDBCUserDetailsService;
import ru.omsu.core.service.jwt.JwtService;
import ru.omsu.core.service.registration.UserRegistrationService;
import ru.omsu.core.service.suite.SuiteService;
import ru.omsu.core.service.testCase.TestCaseService;
import ru.omsu.core.service.testPlan.ITestPlanService;
import ru.omsu.core.service.testPlan.TestPlanService;
import ru.omsu.core.service.tree.TreeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * configuration class for services
 */
@Configuration
public class ServiceConfig {
    /**
     * @param treeRepository repo for requesting cases and suites in tree from db
     * @return Tree service object
     */
    @Bean
    public TreeService treeService(final ITreeRepository treeRepository) {
        return new TreeService(treeRepository);
    }

    /**
     * @param testCaseRepository repo for requesting test cases from db
     * @return TestCaseService object
     */
    @Bean
    public TestCaseService testCaseService(final ITestCaseRepository testCaseRepository) {
        return new TestCaseService(testCaseRepository);
    }

    /**
     * @param suiteRepository repo for requesting suites from db
     * @return SuiteService object
     */
    @Bean
    public SuiteService suiteService(final ISuiteRepository suiteRepository) {
        return new SuiteService(suiteRepository);
    }

    @Bean
    public TestPlanService testPlanService(final ITestPlanRepository testPlanRepository) {
        return new TestPlanService(testPlanRepository);
    }

    @Bean
    public UserRegistrationService userRegistrationService(final UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UserRegistrationService(userRepository, passwordEncoder);
    }

    @Bean
    AuthenticationService authenticationService(JwtService jwtService, AuthenticationManager authenticationManager) {
        return new AuthenticationService(jwtService, authenticationManager);
    }
    @Bean
    public JDBCUserDetailsService JDBCUserDetailsService(final UserRepository userRepository) {
        return new JDBCUserDetailsService(userRepository);
    }
}
