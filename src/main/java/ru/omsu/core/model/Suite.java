package ru.omsu.core.model;

import java.util.ArrayList;
import java.util.UUID;

public class Suite {
    private final String suiteName;
    private final UUID suiteRootId;
    private final UUID suiteId;
    private final int numberOfChild;

    public Suite(String suiteName, UUID suiteId, UUID suiteRootId, int numberOfChild) {
        this.suiteName = suiteName;
        this.suiteId = suiteId;
        this.suiteRootId = suiteRootId;
        this.numberOfChild = numberOfChild;
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

    public int getNumberOfChild() {
        return numberOfChild;
    }
}
