package ru.omsu.core.model;

import java.util.UUID;

public record TestRun(UUID testRunId,UUID testPlanId,String testName) {
}
