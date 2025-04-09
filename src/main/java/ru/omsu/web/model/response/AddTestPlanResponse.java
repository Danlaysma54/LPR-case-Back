package ru.omsu.web.model.response;

import java.util.UUID;

public class AddTestPlanResponse {
    private final UUID testPlan;

    public AddTestPlanResponse(UUID testPlan) {
        this.testPlan = testPlan;
    }

    public UUID getTestPlan() {
        return testPlan;
    }
}
