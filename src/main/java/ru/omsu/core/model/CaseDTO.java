package ru.omsu.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;


public class CaseDTO {
    private final String caseName;
    private final UUID caseId;

    public CaseDTO(String caseName, UUID caseId) {
        this.caseName = caseName;
        this.caseId = caseId;
    }

    public String getCaseName() {
        return caseName;
    }

    public UUID getCaseId() {
        return caseId;
    }
}
