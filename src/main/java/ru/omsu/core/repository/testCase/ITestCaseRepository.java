package ru.omsu.core.repository.testCase;

import ru.omsu.core.model.TestCase;
import ru.omsu.web.model.request.TestCaseRequest;


import java.util.UUID;

/**
 * interface for requests to db on test case entity
 */
public interface ITestCaseRepository {
    /**
     *
     * @param testCaseRequest object for request to add test case
     * @return UUID of added test case
     */
    UUID addTestCase(TestCaseRequest testCaseRequest);

    /**
     *
     * @param testCaseId id of test case
     * @return Test case entity
     */

    TestCase getTestCase(UUID testCaseId);

    /**
     *
     * @param testCaseId id of test case
     */

    void deleteTestCase(UUID testCaseId);

    /**
     *
     * @param testCase entity of test case
     */

    void editTestCase(TestCase testCase);

}
