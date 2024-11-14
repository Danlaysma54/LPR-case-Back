package ru.omsu.core.repository.testCase;

import ru.omsu.core.model.TestCase;

import java.util.UUID;

public interface ITestCaseRepository {
    UUID addTestCase(TestCase testCase);

    TestCase getTestCase(UUID testCaseId);

    void deleteTestCase(UUID testCaseId);

}
