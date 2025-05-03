package ru.omsu.core.service.testPlan;

import ru.omsu.core.model.TestPlan;
import ru.omsu.web.model.request.TestPlanRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.GetTestPlanByIdResponse;
import ru.omsu.web.model.response.GetTestPlansResponse;

import java.util.UUID;

/**
 * service for test plan
 */
public interface ITestPlanService {
    GetTestPlansResponse getTestPlans(UUID projectId);

    AddedEntityResponse addTestPlan(TestPlanRequest testPlanRequest);

    void deleteTestPlan(UUID testPlanId, UUID projectId);

    void editTestPlan(TestPlan testPlan);

    GetTestPlanByIdResponse getTestPlanById(UUID projectId, UUID testPlanId);
}
