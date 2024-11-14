package ru.omsu.core.service.testCase;

import ru.omsu.core.model.TestCase;
import ru.omsu.core.repository.testCase.ITestCaseRepository;
import org.springframework.stereotype.Service;

@Service
public class TestCaseService implements ITestCaseService{
    private final ITestCaseRepository testCaseRepository;

    public TestCaseService(ITestCaseRepository testCaseRepository) {
        this.testCaseRepository = testCaseRepository;
    }

    @Override
    public TestCase addTestCase(TestCase testCase) {
        return testCaseRepository.getTestCase(testCaseRepository.addTestCase(testCase));
    }
}
