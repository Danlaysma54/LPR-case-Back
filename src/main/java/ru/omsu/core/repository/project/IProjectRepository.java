package ru.omsu.core.repository.project;

import ru.omsu.core.model.Project;
import ru.omsu.web.model.request.AddProjectRequest;

import java.util.UUID;

public interface IProjectRepository {

    UUID addProject(AddProjectRequest project);

    Project getProjectById(UUID projectId);

    void deleteProject(UUID projectId);
}
