package ru.omsu.core.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

/**
 * class for entity suite
 */
public class Suite {
    @NotNull(message = "Suite name can't be null")
    @Size(min = 2, max = 255, message = "Suite name has to be from 2 to 255 symbols")
    private final String suiteName;
    @NotNull(message = "Suite id can't be null")
    private final UUID suiteId;
    @NotNull(message = "Suite id can't be null")
    private final UUID suiteRootId;
    private boolean hasChildSuites;

    /**
     * @param suiteName   name of suite
     * @param suiteId     id of suite
     * @param suiteRootId root id of suite
     */
    public Suite(final String suiteName, final UUID suiteId, final UUID suiteRootId) {
        this.suiteName = suiteName;
        this.suiteId = suiteId;
        this.suiteRootId = suiteRootId;
        this.hasChildSuites = false;
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

    public void setHasChildSuites(final boolean hasChildSuites) {
        this.hasChildSuites = hasChildSuites;
    }

    public boolean isHasChildSuites() {
        return hasChildSuites;
    }
}
