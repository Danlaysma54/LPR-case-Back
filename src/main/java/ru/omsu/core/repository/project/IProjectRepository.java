package ru.omsu.core.repository.project;

import ru.omsu.core.model.Project;
import ru.omsu.web.model.request.AddProjectRequest;

import java.util.UUID;

/**
 *  interface for requests to db on project entity
 */
public interface IProjectRepository {
    /**
     *
     * @param project request object to add in db
     * @return UUID of project
     */
    UUID addProject(AddProjectRequest project,UUID userId);

    /**
     *
     * @param projectId id of project
     * @return project entity
     */
    Project getProjectById(UUID projectId);

    /**
     *
     * @param projectId id of project
     */

    void deleteProject(UUID projectId);

    boolean isUserInProject(UUID projectId,UUID userId);
}
