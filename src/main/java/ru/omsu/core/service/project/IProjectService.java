package ru.omsu.core.service.project;

import ru.omsu.core.model.Project;
import ru.omsu.web.model.request.AddProjectRequest;
import ru.omsu.web.model.response.GetProjectResponse;

import java.util.UUID;

public interface IProjectService {

    UUID addProject(AddProjectRequest project);

    GetProjectResponse getProjectById(UUID projectId);

    void deleteProject(UUID projectId);
}
