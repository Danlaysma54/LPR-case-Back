package ru.omsu.core.service.testPlan;

import ru.omsu.core.model.TestPlan;
import ru.omsu.core.repository.testPlan.ITestPlanRepository;
import ru.omsu.web.model.request.AddTestPlanRequest;
import ru.omsu.web.model.response.AddTestPlanResponse;
import ru.omsu.web.model.response.GetTestPlansInProjectResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * class implementation ITestPlanService
 */
public class TestPlanService implements ITestPlanService {
    private final ITestPlanRepository testPlanRepository;

    /**
     * @param testPlanRepository repo for test plans
     */
    public TestPlanService(final ITestPlanRepository testPlanRepository) {
        this.testPlanRepository = testPlanRepository;
    }

    @Override
    public AddTestPlanResponse addTestPlan(UUID projectId, final AddTestPlanRequest testPlanRequest) {
        UUID testPlanId = testPlanRepository.addTestPlan(testPlanRequest);
        testPlanRepository.addTestPlanForProject(testPlanId, projectId);
        for (UUID testCaseId : testPlanRequest.testCases()) {
            testPlanRepository.addTestCasesInTestPlan(testCaseId, testPlanId);
        }
        return new AddTestPlanResponse(testPlanId);
    }

    @Override
    public List<TestPlan> getTestPlansById(final UUID projectId) {
        return List.of();
    }

    @Override
    public GetTestPlansInProjectResponse getTestPlansInProject(UUID projectId, int limit, int offset) {
        GetTestPlansInProjectResponse plans = new GetTestPlansInProjectResponse(new ArrayList<>());
        for (UUID testPlanId : testPlanRepository.getTestPlansIdInProject(projectId, limit, offset)) {
            plans.testPlanList().add(testPlanRepository.getTestPlane(testPlanId));
        }
        return plans;
    }
}
