package ru.omsu.web.model.request;

import java.util.List;
import java.util.UUID;

public record AddTestPlanRequest(String testPlanName, List<UUID> testCases) {}
