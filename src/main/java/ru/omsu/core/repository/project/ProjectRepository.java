package ru.omsu.core.repository.project;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.omsu.core.model.Project;
import ru.omsu.web.model.request.AddProjectRequest;

import java.util.UUID;

@Repository
public class ProjectRepository implements IProjectRepository {
    private final JdbcOperations jdbcOperations;

    public ProjectRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public UUID addProject(AddProjectRequest project) {
        return jdbcOperations.queryForObject("INSERT INTO project(project_name, project_description, project_short_name) VALUES (?,?,?) RETURNING project_id",
                (resultSet, i) -> UUID.fromString(resultSet.getString("project_id")),
                project.getProjectName(), project.getProjectDescription(), project.getProjectShortName());
    }

    @Override
    public void deleteProject(UUID projectId) {
        if (jdbcOperations.update("DELETE FROM project where project_id = CAST(? AS UUID)", projectId.toString()) < 1) {
            throw new IllegalArgumentException("Project with this ID doesn't exist");
        }
    }

    @Override
    public Project getProjectById(UUID projectId) {
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
