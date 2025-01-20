package ru.omsu.web.model.response;

import ru.omsu.core.model.Step;
import ru.omsu.core.model.TestCase;

import java.util.List;

public record GetTestCaseResponse(TestCase testCase, List<Step> stepList) {
}
