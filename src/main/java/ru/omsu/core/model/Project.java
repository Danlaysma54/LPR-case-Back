package ru.omsu.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Project {
    private final UUID projectId;
    private final String projectName;
    private final String projectDescription;
    private final String projectShortName;
}
