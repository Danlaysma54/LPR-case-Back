package ru.omsu.core.service.testCase;

import ru.omsu.core.model.TestCase;
import ru.omsu.core.repository.testCase.ITestCaseRepository;
import org.springframework.stereotype.Service;
import ru.omsu.web.model.request.TestCaseRequest;
import ru.omsu.web.model.response.TestCaseResponse;

import java.util.UUID;

/**
 * implementation class of test case service
 */
@Service
public class TestCaseService implements ITestCaseService {
    private final ITestCaseRepository testCaseRepository;

    /**
     * @param testCaseRepository
     */
    public TestCaseService(ITestCaseRepository testCaseRepository) {
        this.testCaseRepository = testCaseRepository;
    }

    @Override
    public TestCase addTestCase(TestCaseRequest testCaseRequest) {
        return testCaseRepository.getTestCase(testCaseRepository.addTestCase(testCaseRequest));
    }

    @Override
    public void deleteTestCase(UUID testCaseId) {
        this.testCaseRepository.deleteTestCase(testCaseId);
    }

    @Override
    public TestCaseResponse editTestCase(TestCaseRequest testCase) {
        testCaseRepository.editTestCase(testCase);
        return testCaseRepository.getTestCase(testCase.testCaseId());
    }

    @Override
    public TestCaseResponse getTestCase(UUID testCaseId) {
        return new TestCaseResponse(testCaseRepository.getTestCase(testCaseId), testCaseRepository.getTestCaseSteps(testCaseId));
    }
}
