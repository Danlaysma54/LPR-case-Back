package ru.omsu.core.model;

import java.util.List;
import java.util.UUID;

public class TestPlanDTO {
    private final UUID testPlanId;
    private final String testPlanName;

    public TestPlanDTO(UUID testPlanId, String testPlanName) {
        this.testPlanId = testPlanId;
        this.testPlanName = testPlanName;
    }

    public UUID getTestPlanId() {
        return testPlanId;
    }

    public String getTestPlanName() {
        return testPlanName;
    }
}
