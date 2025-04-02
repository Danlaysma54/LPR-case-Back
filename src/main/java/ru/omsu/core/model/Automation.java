package ru.omsu.core.model;

import java.util.UUID;

/**
 * automation type of testcase
 */
public class Automation {

    private final String automationName;

    private final UUID automationId;

    /**
     *
     * @param automationName name of automation
     * @param automationId id of automation
     */
    public Automation(final String automationName, final UUID automationId) {
        this.automationName = automationName;
        this.automationId = automationId;
    }

    public String getAutomationName() {
        return automationName;
    }

    public UUID getAutomationId() {
        return automationId;
    }
}
