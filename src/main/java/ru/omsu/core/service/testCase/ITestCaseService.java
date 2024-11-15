package ru.omsu.core.service.testCase;

import ru.omsu.core.model.TestCase;

public interface ITestCaseService {
    TestCase addTestCase(TestCase testCase);

    TestCase editTestCase(TestCase testCase);

}
