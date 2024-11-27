package ru.omsu.web.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;

public class TestCaseRequest {
    private final UUID suiteId;
    private final String testCaseName;
    private final UUID layerId;
    private final UUID isAutomatedId;

    @JsonCreator
    public TestCaseRequest(UUID suiteId, String testCaseName, UUID layerId, UUID isAutomatedId) {
        this.suiteId = suiteId;
        this.testCaseName = testCaseName;
        this.layerId = layerId;
        this.isAutomatedId = isAutomatedId;
    }

    public UUID getSuiteId() {
        return suiteId;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public UUID getLayerId() {
        return layerId;
    }

    public UUID getIsAutomatedId() {
        return isAutomatedId;
    }
}
