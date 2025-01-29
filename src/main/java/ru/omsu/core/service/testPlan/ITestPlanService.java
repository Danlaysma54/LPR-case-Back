package ru.omsu.core.service.testPlan;

import ru.omsu.core.model.TestPlan;
import ru.omsu.web.model.request.AddTestPlanRequest;
import ru.omsu.web.model.response.AddTestPlanResponse;
import ru.omsu.web.model.response.GetTestPlansInProjectResponse;

import java.util.List;
import java.util.UUID;

/**
 * class service for working with test plans
 */
public interface ITestPlanService {
    /**
     * @param testPlanRequest request to add test plan
     * @return id of added test plan
     */
    AddTestPlanResponse addTestPlan(AddTestPlanRequest testPlanRequest);

    /**
     * @param projectId id of project
     * @return of test plans
     */
    List<TestPlan> getTestPlansById(UUID projectId);

    GetTestPlansInProjectResponse getTestPlansInProject(UUID projectId, int limit, int offset);
}
