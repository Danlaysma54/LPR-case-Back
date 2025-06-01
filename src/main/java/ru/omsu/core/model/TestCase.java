package ru.omsu.core.model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * test case
 */
public class TestCase {
    private final UUID testCaseId;
    private final String testCaseName;
    private final String layer;
    private final String isAutomated;
    private final UUID suiteId;
    private final List<Step> stepList;

    /**
     * @param testCaseId   id of test case
     * @param testCaseName name of the test case
     * @param layer        layer of test case
     * @param isAutomated  is it automated
     * @param suiteId      id of suite
     */
    public TestCase(final UUID testCaseId, final String testCaseName, final String layer, final String isAutomated, final UUID suiteId) {
        this.testCaseId = testCaseId;
        this.testCaseName = testCaseName;
        this.layer = layer;
        this.isAutomated = isAutomated;
        this.suiteId = suiteId;
        this.stepList = new ArrayList<>();
    }

    public UUID getTestCaseId() {
        return testCaseId;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public String getLayer() {
        return layer;
    }

    public String getIsAutomated() {
        return isAutomated;
    }

    public UUID getSuiteId() {
        return suiteId;
    }

    public List<Step> getStepList() {
        return stepList;
    }
}
