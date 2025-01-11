package ru.omsu.config;

import ru.omsu.core.repository.suite.ISuiteRepository;
import ru.omsu.core.repository.testCase.ITestCaseRepository;
import ru.omsu.core.repository.tree.ITreeRepository;
import ru.omsu.core.service.testCase.TestCaseService;
import ru.omsu.core.service.tree.TreeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public TreeService treeService(final ITreeRepository gameRepository, final ISuiteRepository suiteRepository) {
        return new TreeService(gameRepository, suiteRepository);
    }

    @Bean
    public TestCaseService testCaseService(final ITestCaseRepository testCaseRepository) {
        return new TestCaseService(testCaseRepository);
    }

}
