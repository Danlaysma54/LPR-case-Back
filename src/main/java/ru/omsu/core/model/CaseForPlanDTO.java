package ru.omsu.core.model;


import java.util.UUID;

/**
 * @param caseName name of test case
 * @param caseId   UUID of test case
 * @param suiteId  UUID of suite
 */
public record CaseForPlanDTO(String caseName, UUID caseId, UUID suiteId) {

}
