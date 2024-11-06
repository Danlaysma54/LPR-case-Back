package config;

import core.repository.testCase.ITestCaseRepository;
import core.repository.tree.ITreeRepository;
import core.service.testCase.TestCaseService;
import core.service.tree.TreeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public TreeService treeService(final ITreeRepository gameRepository) {
        return new TreeService(gameRepository);
    }

    @Bean
    public TestCaseService testCaseService(final ITestCaseRepository testCaseRepository) {
        return new TestCaseService(testCaseRepository);
    }

}
