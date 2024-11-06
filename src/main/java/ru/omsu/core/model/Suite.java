package ru.omsu.core.model;

import java.util.UUID;

public class Suite {
    private final String suiteName;

    private final UUID suiteId;

    public Suite(String suiteName, UUID suiteId) {
        this.suiteName = suiteName;
        this.suiteId = suiteId;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public UUID getSuiteId() {
        return suiteId;
    }
}
