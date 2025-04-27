package ru.omsu.core.model;

import java.util.UUID;

public class TestRunDTO {
    private final UUID testRunId;
    private final String testRunName;

    public TestRunDTO(UUID testRun, String testRunName) {
        this.testRunId = testRun;
        this.testRunName = testRunName;
    }

    public UUID getTestRunId() {
        return testRunId;
    }

    public String getTestRunName() {
        return testRunName;
    }
}
