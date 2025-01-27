package ru.omsu.web.model.request;

import java.util.List;
import java.util.UUID;

/**
 * class for adding test plan
 */
public class TestPlanRequest {
    private final String testPlanName;
    private final List<UUID> testCaseList;

    /**
     *
     * @param testPlanName name of the test plane
     * @param testCaseList
     */
    public TestPlanRequest(String testPlanName, List<UUID> testCaseList) {
        this.testPlanName = testPlanName;
        this.testCaseList = testCaseList;
    }
}
