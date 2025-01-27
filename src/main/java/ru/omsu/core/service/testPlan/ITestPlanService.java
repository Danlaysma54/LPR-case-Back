package ru.omsu.core.service.testPlan;

import ru.omsu.core.model.TestPlan;
import ru.omsu.web.model.request.AddTestPlanRequest;

import java.util.List;
import java.util.UUID;

public interface ITestPlanService {
    /**
     * @param testPlanRequest request to add test plan
     * @return id of added test plan
     */
    UUID addTestPlan(AddTestPlanRequest testPlanRequest);

    /**
     *
     * @param projectId id of project
     * @return of test plans
     */
    List<TestPlan> getTestPlansById(UUID projectId);
}
