package ru.omsu.web.model.response;

import ru.omsu.core.model.TestPlanDTO;

import java.util.List;

public record GetTestPlansInProjectResponse(List<TestPlanDTO> testPlanList) {
}
