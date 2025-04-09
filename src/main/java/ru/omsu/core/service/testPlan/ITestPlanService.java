package ru.omsu.core.service.testPlan;

import ru.omsu.core.model.TestPlan;
import ru.omsu.web.model.request.TestPlanRequest;
import ru.omsu.web.model.response.AddTestPlanResponse;
import ru.omsu.web.model.response.GetTestPlansResponse;

import java.util.UUID;

/**
 * service for test plan
 */
public interface ITestPlanService {
    GetTestPlansResponse getTestPlans(UUID projectId);

    AddTestPlanResponse addTestPlan(TestPlanRequest testPlanRequest);

    void deleteTestPlan(UUID testPlanId, UUID projectId);

    TestPlan editTestPlan(TestPlan testPlan);
}
