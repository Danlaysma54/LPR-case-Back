package ru.omsu.web.model.request;

import java.util.UUID;

public record AddTestRunRequest(String testRunName, UUID testPlanId) {
}
