package ru.omsu.web.model.request;


import ru.omsu.core.model.Step;

import java.util.List;
import java.util.UUID;

public record TestCaseRequest(UUID suiteId, String testCaseName, UUID layerId, UUID isAutomatedId, List<Step> stepsList) {

}
