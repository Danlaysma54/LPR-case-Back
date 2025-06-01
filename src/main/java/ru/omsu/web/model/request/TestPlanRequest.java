package ru.omsu.web.model.request;

import java.util.List;
import java.util.UUID;

/**
 * class for adding test plan
 */
public class TestPlanRequest {
    private final String testPlanName;
    private final List<UUID> testCases;

    /**
     *
     * @param testPlanName name of the test plane
     * @param testCases
     */
    public TestPlanRequest(String testPlanName, List<UUID> testCases) {
        this.testPlanName = testPlanName;
        this.testCases = testCases;
    }

    public String getTestPlanName() {
        return testPlanName;
    }

    public List<UUID> getTestCases() {
        return testCases;
    }
}
