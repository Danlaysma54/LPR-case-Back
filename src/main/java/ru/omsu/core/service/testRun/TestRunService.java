package ru.omsu.core.service.testRun;

import org.springframework.stereotype.Service;
import ru.omsu.core.model.TestRun;
import ru.omsu.core.repository.testPlan.ITestPlanRepository;
import ru.omsu.core.repository.testRun.ITestRunRepository;
import ru.omsu.core.service.testPlan.ITestPlanService;
import ru.omsu.web.model.request.AddTestRunRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.GetTestRunResponse;

import java.util.UUID;

@Service
public class TestRunService implements ITestRunService {
    private final ITestRunRepository testRunRepository;
    private final ITestPlanRepository testPlanRepository;

    public TestRunService(ITestRunRepository testRunRepository,ITestPlanRepository testPlanRepository) {
        this.testRunRepository = testRunRepository;
       this.testPlanRepository= testPlanRepository;
    }

    @Override
    public GetTestRunResponse getTestRuns(UUID projectId) {
        return new GetTestRunResponse(testRunRepository.getTestRun());
    }

    @Override
    public AddedEntityResponse addTestRun(AddTestRunRequest addTestRunRequest) {
        testPlanRepository.getTestPlanById(addTestRunRequest.testPlanId());
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
