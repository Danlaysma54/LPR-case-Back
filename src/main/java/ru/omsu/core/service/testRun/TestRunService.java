package ru.omsu.core.service.testRun;

import org.springframework.stereotype.Service;
import ru.omsu.core.model.TestRun;
import ru.omsu.core.repository.testRun.ITestRunRepository;
import ru.omsu.web.model.request.AddTestRunRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.GetTestRunResponse;

import java.util.UUID;

@Service
public class TestRunService implements ITestRunService {
    private final ITestRunRepository testRunRepository;

    public TestRunService(ITestRunRepository testRunRepository) {
        this.testRunRepository = testRunRepository;
    }

    @Override
    public GetTestRunResponse getTestRuns(UUID projectId) {
        return new GetTestRunResponse(testRunRepository.getTestRun());
    }

    @Override
    public AddedEntityResponse addTestRun(AddTestRunRequest addTestRunRequest) {
        return new AddedEntityResponse(testRunRepository.addTestRun(addTestRunRequest));
    }

    @Override
    public TestRun editTestRun(TestRun testRun) {
        testRunRepository.editTestRun(testRun);
        return testRunRepository.getTestRunById(testRun.testRunId());
    }

    @Override
    public void deleteTestRun(UUID testRunId) {
        testRunRepository.deleteTestRun(testRunId);
    }
}
