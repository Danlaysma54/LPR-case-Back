package ru.omsu.web.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * request class for adding projects
 */
public class AddProjectRequest {
    @NotNull(message = "Project name cannot be null")
    @Size(min = 3, message = "Project name must be at least 3 characters long")
    private String projectName;
    @NotNull(message = "Project description cannot be null")
    @Size(min = 3, message = "Project name must be at least 3 characters long")
    private String projectDescription;
    @NotNull(message = "Project short name cannot be null")
    @Size(min = 3, max = 5, message = "Project name must be at least 3 characters long")
    private String projectShortName;

    public AddProjectRequest(String projectName, String projectDescription, String projectShortName) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectShortName = projectShortName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getProjectShortName() {
        return projectShortName;
    }
}
