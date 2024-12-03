package ru.omsu.core.model;

import java.util.ArrayList;
import java.util.UUID;

public class Suite {
    private final String suiteName;
    private final UUID suiteRootId;
    private final UUID suiteId;

    public Suite(String suiteName, UUID suiteId, UUID suiteRootId) {
        this.suiteName = suiteName;
        this.suiteId = suiteId;
        this.suiteRootId = suiteRootId;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public UUID getSuiteId() {
        return suiteId;

    }

    public UUID getSuiteRootId() {
        return suiteRootId;
    }
}
