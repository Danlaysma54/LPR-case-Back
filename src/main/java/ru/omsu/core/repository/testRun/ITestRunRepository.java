package ru.omsu.core.repository.testRun;

import ru.omsu.core.model.TestRun;
import ru.omsu.core.model.TestRunDTO;
import ru.omsu.web.model.request.AddTestRunRequest;

import java.util.List;
import java.util.UUID;

public interface ITestRunRepository {
    List<TestRunDTO> getTestRun();

    UUID addTestRun(AddTestRunRequest addTestRunRequest);

    void editTestRun(TestRun testRun);

    TestRun getTestRunById(UUID testRunId);

    void deleteTestRun(UUID testRunId);
}
