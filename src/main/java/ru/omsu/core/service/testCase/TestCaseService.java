package ru.omsu.core.service.testCase;

import ru.omsu.core.model.TestCase;
import ru.omsu.core.repository.testCase.ITestCaseRepository;
import org.springframework.stereotype.Service;
import ru.omsu.web.model.request.TestCaseRequest;

import java.util.UUID;

/**
 * implementation class of test case service
 */
@Service
public class TestCaseService implements ITestCaseService {
    private final ITestCaseRepository testCaseRepository;

    /**
     * <<<<<<< HEAD
     *
     * @param testCaseRepository class for working with db
     *                           =======
     * @param testCaseRepository >>>>>>> develop
     */
    public TestCaseService(final ITestCaseRepository testCaseRepository) {
        this.testCaseRepository = testCaseRepository;
    }

    /**
     * @param testCaseRequest object to add request
     * @return testCase entity
     */
    @Override
    public UUID addTestCase(final TestCaseRequest testCaseRequest) {
        return testCaseRepository.addTestCase(testCaseRequest);
    }

    /**
     * @param testCaseId id of test case
     */
    @Override
    public void deleteTestCase(final UUID testCaseId) {
        this.testCaseRepository.deleteTestCase(testCaseId);
    }

    /**
     * @param testCase test case entity
     * @return testcase
     */
    @Override
    public TestCase editTestCase(final TestCase testCase) {
        testCaseRepository.editTestCase(testCase);
        return testCaseRepository.getTestCase(testCase.getTestCaseId());
    }

    /**
     *
     * @param testCaseId id of seek test case
     * @return TestCase entity
     */
    @Override
    public TestCase getTestCase(final UUID testCaseId) {
        TestCase testCase = testCaseRepository.getTestCase(testCaseId);
        testCase.getStepList().addAll(testCaseRepository.getTestCaseSteps(testCaseId));
        return testCase;
    }
}
