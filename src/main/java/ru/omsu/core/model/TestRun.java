package ru.omsu.core.model;

import java.util.UUID;

public class TestRun {
    private final UUID testRun;
    private final String testRunName;

    public TestRun(UUID testRun, String testRunName) {
        this.testRun = testRun;
        this.testRunName = testRunName;
    }

    public UUID getTestRun() {
        return testRun;
    }

    public String getTestRunName() {
        return testRunName;
    }
}
