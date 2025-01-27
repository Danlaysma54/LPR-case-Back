package ru.omsu.core.repository.testPlan;

import java.util.UUID;

/**
 * Class for working with db for test plan repository
 */
public class TestPlanRepository implements ITestPlanRepository {

    @Override
    public UUID addTestPlan(TestPlanRequest testPlanRequest) {

    }

    @Override
    public TestPlan getTestPlan(UUID testPlanId) {
        return null;
    }

    @Override
    public TestPlan editTestPlan(TestPlan testPlan) {
        return null;
    }

    @Override
    public void deleteTestPlan(UUID testPlanId) {

    }
}
