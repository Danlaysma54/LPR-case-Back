package ru.omsu.core.repository.testPlan;

import java.util.UUID;

/**
 * Interface for working with db for test plan entity
 */
public interface ITestPlanRepository {
    /**
     * @param testPlanRequest request for test plan
     * @return added test plan
     */
  //  UUID addTestPlan(TestPlanRequest testPlanRequest);

    /**
     * @param testPlanId id of plan
     * @return testPlan entity
     */
  //  TestPlan getTestPlan(UUID testPlanId);

    /**
     *
     * @param testPlan new version of test plan
     * @return test plan of db
     */
  //  TestPlan editTestPlan(TestPlan testPlan);

    /**
     *
     * @param testPlanId id of test plan
     */
    void deleteTestPlan(UUID testPlanId);
}
