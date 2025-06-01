package ru.omsu.core.service.testCase;

import ru.omsu.core.model.TestCase;
import ru.omsu.web.model.request.EditTestCaseRequest;
import ru.omsu.web.model.request.TestCaseRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.TestCaseTypes;

import java.util.UUID;

/**
 * interface of test case service
 */
public interface ITestCaseService {
    /**
     * @param testCaseRequest object to add request
     * @return Test case entity
     */
    AddedEntityResponse addTestCase(TestCaseRequest testCaseRequest);

    /**
     * @param testCaseId id of test case
     */
    void deleteTestCase(UUID testCaseId);

    /**
     * @param testCase test case entity
     * @return new Test case
     */
    TestCase editTestCase(EditTestCaseRequest testCase);

    /**
     * @param testCaseId id of seek test case
     * @return testcase entity
     */
    TestCase getTestCase(UUID testCaseId);

    /**
     *
     * @return all test case types
     */
    TestCaseTypes getTestCaseTypes();
}
