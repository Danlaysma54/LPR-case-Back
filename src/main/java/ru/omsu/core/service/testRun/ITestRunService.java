package ru.omsu.core.service.testRun;

import ru.omsu.web.model.response.GetTestRunResponse;

import java.util.UUID;

public interface ITestRunService {
   private final ITestRunRepository testRunRepository;

    public GetTestRunResponse getTestRuns(UUID projectId) {

    }
}
