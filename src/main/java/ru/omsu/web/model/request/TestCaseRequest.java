package ru.omsu.web.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;

public record TestCaseRequest(UUID suiteId, String testCaseName, UUID layerId, UUID isAutomatedId) {
    @JsonCreator
    public TestCaseRequest {
    }
}
