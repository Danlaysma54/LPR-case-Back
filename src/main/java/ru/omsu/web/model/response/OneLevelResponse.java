package ru.omsu.web.model.response;

import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.SuiteDTO;

import java.util.List;


/**
 * class response of one level
 */
public record OneLevelResponse(List<CaseDTO> cases, List<SuiteDTO> suites) {

}
