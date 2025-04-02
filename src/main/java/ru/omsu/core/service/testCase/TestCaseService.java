package ru.omsu.core.service.testCase;

import ru.omsu.core.model.TestCase;
import ru.omsu.core.repository.testCase.ITestCaseRepository;
import org.springframework.stereotype.Service;
import ru.omsu.web.model.request.StepsRequest;
import ru.omsu.web.model.request.TestCaseRequest;
import ru.omsu.web.model.response.AddTestCaseResponse;
import ru.omsu.web.model.response.TestCaseTypes;

import java.util.UUID;

/**
 * implementation class of test case service
 */
@Service
public class TestCaseService implements ITestCaseService {
    private final ITestCaseRepository testCaseRepository;

    /**
     * @param testCaseRepository class for working with db
     * @param testCaseRepository
     */
    public TestCaseService(final ITestCaseRepository testCaseRepository) {
        this.testCaseRepository = testCaseRepository;
    }

    /**
     * @param testCaseRequest object to add request
     * @return testCase entity
     */
    @Override
    public AddTestCaseResponse addTestCase(final TestCaseRequest testCaseRequest) {
        UUID testCaseID = testCaseRepository.addTestCase(testCaseRequest);
        for (StepsRequest steps : testCaseRequest.steps()) {
            testCaseRepository.addTestSteps(steps, testCaseID);
        }
        return new AddTestCaseResponse(testCaseID);
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
     * @param testCaseId id of seek test case
     * @return TestCase entity
     */
    @Override
    public TestCase getTestCase(final UUID testCaseId) {
        TestCase testCase = testCaseRepository.getTestCase(testCaseId);
        testCase.getStepList().addAll(testCaseRepository.getTestCaseSteps(testCaseId));
        return testCase;
    }

    @Override
    public TestCaseTypes getTestCaseTypes() {
        return new TestCaseTypes(testCaseRepository.getTestCaseLayers(), testCaseRepository.getTestCaseAutomation());
    }
}
