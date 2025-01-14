package ru.omsu.web.model.response;

import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.Suite;

import java.util.List;
import java.util.UUID;

/**
 * class response of one level
 */
public record OneLevelResponse(List<CaseDTO> cases, List<Suite> suites, String suiteName, UUID suiteId) {
}
