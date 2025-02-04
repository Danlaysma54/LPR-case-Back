package ru.omsu.core.repository.testPlan;

import ru.omsu.core.model.TestPlan;
import ru.omsu.core.model.TestPlanDTO;
import ru.omsu.web.model.request.AddTestPlanRequest;

import java.util.List;
import java.util.UUID;

/**
 * interface for working
 */
public interface ITestPlanRepository {
    /**
     * @param addTestPlanRequest for add test plan
     * @return id of added test plan
     */
    UUID addTestPlan(AddTestPlanRequest addTestPlanRequest);

    void addTestCasesInTestPlan(UUID testCaseId, UUID projectId);

    List<UUID> getTestPlansIdInProject(UUID projectId, int limit, int offset);

    TestPlanDTO getTestPlane(UUID testPlanId);

    void addTestPlanForProject(UUID testPlanId,UUID projectId);
}
