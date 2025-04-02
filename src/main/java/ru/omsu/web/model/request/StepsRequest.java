package ru.omsu.web.model.request;

/**
 * steps of adding test case
 */
public class StepsRequest {
    private final String stepDescription;
    private final String stepData;
    private final String stepResult;
    private final int stepNumber;

    /**
     *
     * @param stepDescription description of steps
     * @param stepData data of steps
     * @param stepResult results of step
     * @param stepNumber number of step
     */
    public StepsRequest(final String stepDescription, final String stepData, final String stepResult, final int stepNumber) {
        this.stepDescription = stepDescription;
        this.stepData = stepData;
        this.stepResult = stepResult;
        this.stepNumber = stepNumber;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public String getStepData() {
        return stepData;
    }

    public String getStepResult() {
        return stepResult;
    }

    public int getStepNumber() {
        return stepNumber;
    }
}
