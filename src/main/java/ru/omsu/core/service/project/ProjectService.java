package ru.omsu.core.service.project;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.Project;
import ru.omsu.core.model.Suite;
import ru.omsu.core.repository.project.IProjectRepository;
import ru.omsu.core.repository.testCase.TestCaseRepository;
import ru.omsu.core.repository.tree.TreeRepository;
import ru.omsu.web.model.request.AddProjectRequest;
import ru.omsu.web.model.response.AddProjectResponse;
import ru.omsu.web.model.response.GetProjectResponse;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ProjectService implements IProjectService {

    private final TestCaseRepository testCaseRepository;
    private final TreeRepository treeRepository;
    private IProjectRepository projectRepository;

    public ProjectService(IProjectRepository projectRepository, @Qualifier("testCaseRepository") TestCaseRepository testCaseRepository, TreeRepository treeRepository) {
        this.projectRepository = projectRepository;
        this.testCaseRepository = testCaseRepository;
        this.treeRepository = treeRepository;
    }

    @Override
    public UUID addProject(AddProjectRequest project) {
        return projectRepository.addProject(project);
    }

    @Override
    public void deleteProject(UUID projectId) {
        projectRepository.deleteProject(projectId);
    }

    @Override
    public GetProjectResponse getProjectById(UUID projectId)
    {
        Project project = projectRepository.getProjectById(projectId);
        ArrayList<Suite> projectSuites = (ArrayList<Suite>) treeRepository.getAllSuites(projectId);
        ArrayList<CaseDTO> projectCases = new ArrayList<>();
        for (Suite suite : projectSuites) {
            projectCases.addAll(treeRepository.getOneLevelCases(suite.getSuiteId()));
        }
        return new GetProjectResponse(project, projectCases.size(), projectSuites.size());
    }
}
