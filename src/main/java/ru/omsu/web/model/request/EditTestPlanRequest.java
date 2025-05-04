package ru.omsu.web.model.request;

import ru.omsu.core.model.CaseDTO;

import java.util.List;
import java.util.UUID;

public class EditTestPlanRequest {
    private final UUID testPlanId;
    private final String testPlanName;
    private final List<UUID> testCases;

    public EditTestPlanRequest(UUID testPlanId, String testPlanName, List<UUID> testCases) {
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

    public List<UUID> getTestCases() {
        return testCases;
    }
}
