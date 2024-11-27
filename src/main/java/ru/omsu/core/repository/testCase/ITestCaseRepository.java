package ru.omsu.core.repository.testCase;

import ru.omsu.core.model.TestCase;
import ru.omsu.web.model.request.TestCaseRequest;

import java.util.UUID;

public interface ITestCaseRepository {
    UUID addTestCase(TestCaseRequest testCaseRequest);

    TestCase getTestCase(UUID testCaseId);

}
