package ru.omsu.core.service.testPlan;

import ru.omsu.core.model.TestPlan;
import ru.omsu.core.repository.testPlan.ITestPlanRepository;
import ru.omsu.web.model.request.AddTestPlanRequest;

import java.util.List;
import java.util.UUID;

/**
 * class implementation ITestPlanService
 */
public class TestPlanService implements ITestPlanService {
    private final ITestPlanRepository testPlanRepository;

    public TestPlanService(ITestPlanRepository testPlanRepository) {
        this.testPlanRepository = testPlanRepository;
    }

    @Override
    public UUID addTestPlan(AddTestPlanRequest testPlanRequest) {
        UUID testPlanId = testPlanRepository.addTestPlan(testPlanRequest);
        for (UUID testCaseId : testPlanRequest.testCases()) {
            testPlanRepository.addTestCasesInTestPlan(testCaseId, testPlanId);
        }
        return testPlanId;
    }

    @Override
    public List<TestPlan> getTestPlansById(UUID projectId) {
        return List.of();
    }
}
