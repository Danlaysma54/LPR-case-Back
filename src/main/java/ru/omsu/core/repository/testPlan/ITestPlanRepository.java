package ru.omsu.core.repository.testPlan;

import ru.omsu.core.model.*;
import ru.omsu.web.model.request.EditTestPlanRequest;
import ru.omsu.web.model.request.TestPlanRequest;

import java.util.List;
import java.util.UUID;

/**
 * Interface for working with db for test plan entity
 */
public interface ITestPlanRepository {
    /**
     * @param testPlanRequest request for test plan
     * @return added test plan
     */
    UUID addTestPlan(TestPlanRequest testPlanRequest);

    void addTestCaseInTestPlan(UUID testCaseId, UUID testPlanId);

    /**
     * @param projectId of plan
     * @return testPlanDTO entity
     */
    List<TestPlanDTO> getTestPlans(UUID projectId);

    List<CaseDTO> getCaseInTestPlans(UUID testPlanId);

    List<CaseForPlanDTO> getCaseWithSuitesInTestPlans(UUID testPlanId);

    /**
     * @param testPlan new version of test plan
     */
    void editTestPlanName(EditTestPlanRequest testPlan);

    /**
     * @param testPlanId id of test plan
     */
    void deleteTestPlan(UUID testPlanId);

    void editTestCasesInTestPlan(UUID testCaseId);

    void deleteAllTestCasesInTestPlan(UUID testPlanId);

    TestPlanWithSuitesId getTestPlanById(UUID testPlanId);

}
