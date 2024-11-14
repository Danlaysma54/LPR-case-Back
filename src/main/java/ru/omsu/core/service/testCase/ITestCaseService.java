package ru.omsu.core.service.testCase;

import ru.omsu.core.model.TestCase;

import java.util.UUID;

public interface ITestCaseService {
    TestCase addTestCase(TestCase testCase);

    void deleteTestCase(UUID testCaseId);

}
