package ru.omsu.core.model;

import java.util.List;
import java.util.UUID;

public class TestPlanWithSuitesId {
    private final UUID testPlanId;
    private final String testPlanName;
    private final List<CaseForPlanDTO> testCases;

    public TestPlanWithSuitesId(UUID testPlanId, String testPlanName, List<CaseForPlanDTO> testCases) {
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

    public List<CaseForPlanDTO> getTestCases() {
        return testCases;
    }

}
