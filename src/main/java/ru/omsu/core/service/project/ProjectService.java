package ru.omsu.core.service.project;


import org.springframework.stereotype.Service;
import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.Project;
import ru.omsu.core.model.Suite;
import ru.omsu.core.repository.project.IProjectRepository;
import ru.omsu.core.repository.tree.TreeRepository;
import ru.omsu.web.model.request.AddProjectRequest;
import ru.omsu.web.model.response.GetProjectResponse;

import java.util.ArrayList;
import java.util.UUID;

/**
 *  implementation class of project service
 */
@Service
public class ProjectService implements IProjectService {

    private final TreeRepository treeRepository;
    private final IProjectRepository projectRepository;

    /**
     *
     * @param projectRepository object of project repository
     * @param treeRepository object of tree repository
     */
    public ProjectService(final IProjectRepository projectRepository, final TreeRepository treeRepository) {
        this.projectRepository = projectRepository;
        this.treeRepository = treeRepository;
    }

    /**
     *
     * @param project project request object to add in db
     * @return UUID added project
     */
    @Override
    public UUID addProject(final AddProjectRequest project) {
        return projectRepository.addProject(project);
    }

    /**
     *
     * @param projectId id of project
     */
    @Override
    public void deleteProject(final UUID projectId) {
        projectRepository.deleteProject(projectId);
    }

    @Override
    public GetProjectResponse getProjectById(final UUID projectId) {
        return new GetProjectResponse(projectRepository.getProjectById(projectId),0,0);
    }
}
