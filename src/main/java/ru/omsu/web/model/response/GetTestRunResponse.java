package ru.omsu.web.model.response;

import ru.omsu.core.model.TestRun;

import java.util.List;

public class GetTestRunResponse {
    private final List<TestRun> testRunList;

    public GetTestRunResponse(List<TestRun> testRunList) {
        this.testRunList = testRunList;
    }

    public List<TestRun> getTestRunList() {
        return testRunList;
    }
}
