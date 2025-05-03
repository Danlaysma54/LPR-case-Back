package ru.omsu.web.model.response;

import ru.omsu.core.model.TestPlan;

public class GetTestPlanByIdResponse {
    private final TestPlan testPlan;

    public GetTestPlanByIdResponse(TestPlan testPlan) {
        this.testPlan = testPlan;
    }

    public TestPlan getTestPlan() {
        return testPlan;
    }
}
