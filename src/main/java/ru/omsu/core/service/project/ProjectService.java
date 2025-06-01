package ru.omsu.core.service.project;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import ru.omsu.core.repository.project.IProjectRepository;
import ru.omsu.core.repository.tree.TreeRepository;
import ru.omsu.web.model.request.AddProjectRequest;
import ru.omsu.web.model.response.GetProjectResponse;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.UUID;

/**
 *  implementation class of project service
 */
@Service
public class ProjectService implements IProjectService {
    private final JwtDecoder jwtDecoder;
    private final IProjectRepository projectRepository;

    /**
     *
     * @param projectRepository object of project repository
     */
    public ProjectService(final IProjectRepository projectRepository,final JwtDecoder jwtDecoder) {
        this.projectRepository = projectRepository;
        this.jwtDecoder=jwtDecoder;
    }

    /**
     *
     * @param project project request object to add in db
     * @return UUID added project
     */
    @Override
    public UUID addProject(final AddProjectRequest project,String authHeader) {
        UUID userId=extractUserIdFromToken(authHeader);
        return projectRepository.addProject(project,userId);
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
    private UUID extractUserIdFromToken(String authHeader){

        Jwt jwt = jwtDecoder.decode(authHeader.substring(7));
        return UUID.fromString(jwt.getClaim("userId")); // Преобразуем строку в UUID
    }
}
