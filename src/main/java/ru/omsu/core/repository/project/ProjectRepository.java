package ru.omsu.core.repository.project;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.omsu.core.model.Project;
import ru.omsu.web.model.request.AddProjectRequest;

import java.util.UUID;

/**
 * class implementation of Project Repository by postgres
 */
@Repository
public class ProjectRepository implements IProjectRepository {
    private final JdbcOperations jdbcOperations;

    /**
     *
     * @param jdbcOperations jdbc
     */
    public ProjectRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    /**
     *
     * @param project request object to add in db
     * @return UUID of added project
     */
    @Override
    public UUID addProject(final AddProjectRequest project) {
        return jdbcOperations.queryForObject("INSERT INTO project(project_name, project_description, project_short_name) VALUES (?,?,?) RETURNING project_id",
                (resultSet, i) -> UUID.fromString(resultSet.getString("project_id")),
                project.getProjectName(), project.getProjectDescription(), project.getProjectShortName());
    }

    /**
     *
     * @param projectId id of project
     *
     */
    @Override
    public void deleteProject(final UUID projectId) {
        if (jdbcOperations.update("DELETE FROM project where project_id = CAST(? AS UUID)", projectId.toString()) < 1) {
            throw new IllegalArgumentException("Project with this ID doesn't exist");
        }
    }

    /**
     *
     * @param projectId id of project
     * @return project entity
     */
    @Override
    public Project getProjectById(final UUID projectId) {
        try {
            return jdbcOperations.queryForObject("select project_id,project_name,project_description,project_short_name from project where project_id=?",
                    (resultSet, i) -> new Project(
                            UUID.fromString(resultSet.getString("project_id")),
                            resultSet.getString("project_name"),
                            resultSet.getString("project_description"),
                            resultSet.getString("project_short_name")),
                    projectId);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Project with this ID doesn't exist");

        }
    }
}
