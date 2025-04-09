package ru.omsu.config;

import ru.omsu.core.repository.suite.ISuiteRepository;
import ru.omsu.core.repository.testCase.ITestCaseRepository;
import ru.omsu.core.repository.testPlan.ITestPlanRepository;
import ru.omsu.core.repository.testPlan.TestPlanRepository;
import ru.omsu.core.repository.tree.ITreeRepository;
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
}
