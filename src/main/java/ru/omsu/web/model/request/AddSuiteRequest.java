package ru.omsu.web.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

/**
 * class request to add suite
 */
public class AddSuiteRequest {
    @NotNull(message = "Suite name can't be null")
    @Size(min = 2, max = 255, message = "Suite name has to be from 2 to 255 symbols")
    private final String suiteName;
    @NotNull(message = "Suite id can't be null")
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
