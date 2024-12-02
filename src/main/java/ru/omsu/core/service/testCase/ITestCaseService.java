package ru.omsu.core.service.testCase;

import ru.omsu.core.model.TestCase;
import ru.omsu.web.model.request.TestCaseRequest;

import java.util.UUID;

public interface ITestCaseService {
    TestCase addTestCase(TestCaseRequest testCaseRequest);

    void deleteTestCase(UUID testCaseId);

    TestCase editTestCase(TestCase testCase);

}
