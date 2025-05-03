package ru.omsu.web.model.response;

import ru.omsu.core.model.TestPlan;
import ru.omsu.core.model.TestPlanWithSuitesId;

public class GetTestPlanByIdResponse {
    private final TestPlanWithSuitesId testPlan;

    public GetTestPlanByIdResponse(TestPlanWithSuitesId testPlan) {
        this.testPlan = testPlan;
    }

    public TestPlanWithSuitesId getTestPlan() {
        return testPlan;
    }
}
