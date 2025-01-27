package ru.omsu.core.service.testPlan;

import ru.omsu.web.model.request.AddTestPlanRequest;

import java.util.UUID;

public interface ITestPlanService {
    /**
     *
     * @param testPlanRequest request to add test plan
     * @return id of added test plan
     */
    UUID addTestPlan(AddTestPlanRequest testPlanRequest);
}
