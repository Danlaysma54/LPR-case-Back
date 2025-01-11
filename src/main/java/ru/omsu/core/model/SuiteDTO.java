package ru.omsu.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@AllArgsConstructor
@Data
public class SuiteDTO {
    private final UUID suiteId;
    private final String suiteName;
    private final UUID suiteRootId;
}
