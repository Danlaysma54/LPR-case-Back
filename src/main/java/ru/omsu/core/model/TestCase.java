package ru.omsu.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;

public class TestCase {
    private final UUID testCaseId;
    private final String testCaseName;
    private final String layer;
    private final String isAutomated;
    private final UUID suiteId;

    public UUID getSuiteId() {
        return suiteId;
    }

    public TestCase(UUID testCaseId, String testCaseName, String layer, String isAutomated, UUID suiteId) {
        this.testCaseId = testCaseId;
        this.testCaseName = testCaseName;
        this.layer = layer;
        this.isAutomated = isAutomated;
        this.suiteId = suiteId;

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
}
