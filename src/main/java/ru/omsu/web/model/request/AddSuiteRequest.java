package ru.omsu.web.model.request;

import java.util.UUID;

/**
 * class request to add suite
 */
public class AddSuiteRequest {
    private final String suiteName;
    private final UUID suiteRootId;

    /**
     *
     * @param suiteName suite name
     * @param suitRootId root id
     */
    public AddSuiteRequest(final String suiteName, final UUID suitRootId) {
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
