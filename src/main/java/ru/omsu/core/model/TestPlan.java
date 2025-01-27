package ru.omsu.core.model;

import java.util.List;
import java.util.UUID;

public record TestPlan(UUID testPlanId, String testPlanName, List<CaseDTO> caseDTOList) {
}
