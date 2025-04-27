package ru.omsu.core.service.testRun;

import ru.omsu.core.model.TestRun;
import ru.omsu.web.model.request.AddTestRunRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.GetTestRunResponse;

import java.util.UUID;

public interface ITestRunService {
    GetTestRunResponse getTestRuns(UUID projectId);

    AddedEntityResponse addTestRun(AddTestRunRequest addTestRunRequest);

    TestRun editTestRun(TestRun testRun);

    void deleteTestRun(UUID testRunId);
}
