package ru.omsu.web.model.request;

import java.util.UUID;

public class AddSuiteRequest {
    private final String suiteName;
    private final UUID suiteRootId;

    public AddSuiteRequest(String suiteName, UUID suitRootId) {
        this.suiteName = suiteName;
        this.suiteRootId = suitRootId;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public UUID getSuiteRootId() {
        return suiteRootId;
    }
}
