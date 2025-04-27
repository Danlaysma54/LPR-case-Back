package ru.omsu.web.model.response;

import ru.omsu.core.model.TestRunDTO;

import java.util.List;

public record GetTestRunResponse(List<TestRunDTO> testRunDTOList) {
}
