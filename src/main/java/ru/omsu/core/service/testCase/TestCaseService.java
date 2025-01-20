package ru.omsu.core.service.testCase;

import ru.omsu.core.model.TestCase;
import ru.omsu.core.repository.testCase.ITestCaseRepository;
import org.springframework.stereotype.Service;
import ru.omsu.web.model.request.TestCaseRequest;
import ru.omsu.web.model.response.GetTestCaseResponse;

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
    public TestCase editTestCase(TestCase testCase) {
        testCaseRepository.editTestCase(testCase);
        return testCaseRepository.getTestCase(testCase.testCaseId());
    }

    @Override
    public GetTestCaseResponse getTestCase(UUID testCaseId) {
        return new GetTestCaseResponse(testCaseRepository.getTestCase(testCaseId), testCaseRepository.getTestCaseSteps(testCaseId));
    }
}
