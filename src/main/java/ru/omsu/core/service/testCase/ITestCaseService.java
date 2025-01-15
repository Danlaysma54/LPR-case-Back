package ru.omsu.core.service.testCase;

import ru.omsu.core.model.TestCase;
import ru.omsu.web.model.request.TestCaseRequest;

import java.util.UUID;

/**
 * interface of test case service
 */
public interface ITestCaseService {
    /**
     *
     * @param testCaseRequest object to add request
     * @return Test case entity
     */
    TestCase addTestCase(TestCaseRequest testCaseRequest);

    /**
     *
     * @param testCaseId id of test case
     */
    void deleteTestCase(UUID testCaseId);

    /**
     *
     * @param testCase test case entity
     * @return new Test case
     */
    TestCase editTestCase(TestCase testCase);

}
