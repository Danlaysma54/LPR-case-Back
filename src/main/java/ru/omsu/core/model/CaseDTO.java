package ru.omsu.core.model;



import java.util.UUID;

/**
 *
 * @param caseName name of test case
 * @param caseId UUID of test case
 */
public record CaseDTO(String caseName, UUID caseId) {
}
