package ru.omsu.web.model.request;


import java.util.UUID;

public record TestCaseRequest(UUID suiteId, String testCaseName, UUID layerId, UUID isAutomatedId) {

}
