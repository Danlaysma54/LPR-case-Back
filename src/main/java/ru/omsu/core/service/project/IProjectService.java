package ru.omsu.core.service.project;


import ru.omsu.web.model.request.AddProjectRequest;
import ru.omsu.web.model.response.GetProjectResponse;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

/**
 *  interface for project service
 */
public interface IProjectService {
    /**
     *
     * @param project project request object to add in db
     * @return UUID of added project
     */

    UUID addProject(AddProjectRequest project,String authHeader);

    /**
     *
     * @param projectId id of project
     * @return Response for project
     */

    GetProjectResponse getProjectById(UUID projectId);

    /**
     *
     * @param projectId id of project
     */

    void deleteProject(UUID projectId);
}
