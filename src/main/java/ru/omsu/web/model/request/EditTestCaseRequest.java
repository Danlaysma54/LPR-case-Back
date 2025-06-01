package ru.omsu.web.model.request;


import ru.omsu.core.model.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * test case
 */
public class EditTestCaseRequest {
    private final UUID testCaseId;
    private final String testCaseName;
    private final UUID layer;
    private final UUID isAutomated;
    private final UUID suiteId;
    private final List<Step> stepList;

    /**
     * @param testCaseId   id of test case
     * @param testCaseName name of the test case
     * @param layer        layer of test case
     * @param isAutomated  is it automated
     * @param suiteId      id of suite
     */
    public EditTestCaseRequest(final UUID testCaseId, final String testCaseName, final UUID layer, final UUID isAutomated, final UUID suiteId) {
        this.testCaseId = testCaseId;
        this.testCaseName = testCaseName;
        this.layer = layer;
        this.isAutomated = isAutomated;
        this.suiteId = suiteId;
        this.stepList = new ArrayList<>();
    }

    public UUID getTestCaseId() {
        return testCaseId;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public UUID getLayer() {
        return layer;
    }

    public UUID getIsAutomated() {
        return isAutomated;
    }

    public UUID getSuiteId() {
        return suiteId;
    }

    public List<Step> getStepList() {
        return stepList;
    }
}
