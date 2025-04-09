package ru.omsu.web.model.response;

import ru.omsu.core.model.TestPlan;

import java.util.List;

public class GetTestPlansResponse {
    private List<TestPlan> testPlans;

    public GetTestPlansResponse(List<TestPlan> testPlans) {
        this.testPlans = testPlans;
    }

    public List<TestPlan> getTestPlans() {
        return testPlans;
    }
}
