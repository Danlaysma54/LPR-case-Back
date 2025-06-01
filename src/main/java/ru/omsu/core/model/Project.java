package ru.omsu.core.model;


import java.util.UUID;

/**
 *
 * @param projectId UUID of project
 * @param projectName name of project
 * @param projectDescription description for project
 * @param projectShortName Name for project,which has 3 symbols or fewer
 */

public record Project
        (UUID projectId, String projectName, String projectDescription, String projectShortName) {
}
