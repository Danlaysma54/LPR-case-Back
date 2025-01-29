package ru.omsu.web.model.response;

import ru.omsu.core.model.TestPlan;

import java.util.List;

public record GetTestPlansInProjectResponse(List<TestPlan> testPlanList) {
}
