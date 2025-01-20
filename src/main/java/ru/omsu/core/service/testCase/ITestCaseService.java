package ru.omsu.core.service.testCase;

import ru.omsu.core.model.TestCase;
import ru.omsu.web.model.request.EditTestCaseRequest;
import ru.omsu.web.model.request.TestCaseRequest;
import ru.omsu.web.model.response.TestCaseResponse;

import java.util.UUID;

/**
 * interface of test case service
 */
public interface ITestCaseService {
    /**
     * @param testCaseRequest object to add request
     * @return Test case entity
     */
    TestCase addTestCase(TestCaseRequest testCaseRequest);

    /**
     * @param testCaseId id of test case
     */
    void deleteTestCase(UUID testCaseId);

    /**
     * @param testCase test case entity
     * @return new Test case
     */
    TestCaseResponse editTestCase(TestCaseRequest testCase);

    TestCaseResponse getTestCase(UUID suiteId);
}
