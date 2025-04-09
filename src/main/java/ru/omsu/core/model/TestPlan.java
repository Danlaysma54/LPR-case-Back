package ru.omsu.core.model;

import java.util.List;
import java.util.UUID;

public class TestPlan {
    private final UUID testPlanId;
    private final String testPlanName;
    private final List<CaseDTO> testCases;

    public TestPlan(UUID testPlanId, String testPlanName, List<CaseDTO> testCases) {
        this.testPlanId = testPlanId;
        this.testPlanName = testPlanName;
        this.testCases = testCases;
    }

    public UUID getTestPlanId() {
        return testPlanId;
    }

    public String getTestPlanName() {
        return testPlanName;
    }

    public List<CaseDTO> getTestCases() {
        return testCases;
    }

}
