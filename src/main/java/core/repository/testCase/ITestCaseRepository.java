package core.repository.testCase;

import core.model.TestCase;

import java.util.UUID;

public interface ITestCaseRepository {
    UUID addTestCase(TestCase testCase);

    TestCase getTestCase(UUID testCaseId);

}
