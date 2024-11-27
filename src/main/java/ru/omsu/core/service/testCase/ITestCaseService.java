package ru.omsu.core.service.testCase;

import ru.omsu.core.model.TestCase;
import ru.omsu.web.model.request.TestCaseRequest;

public interface ITestCaseService {
    TestCase addTestCase(TestCaseRequest testCaseRequest);

}
