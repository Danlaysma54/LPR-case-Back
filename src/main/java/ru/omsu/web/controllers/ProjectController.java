package ru.omsu.web.controllers;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import ru.omsu.core.service.project.IProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.omsu.web.model.request.AddProjectRequest;
import ru.omsu.web.model.response.AddProjectResponse;

import java.util.UUID;

/**
 * class controller
 */
@RestController
@RequestMapping("/")
public class ProjectController {
    private final IProjectService projectService;

    /**
     *
     * @param projectService service of project
     */
    public ProjectController(final IProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     *
     * @param addProjectRequest object request to add project
     * @return Add project response
     */
    @PostMapping("/addProject")
    @ResponseBody
    public ResponseEntity<AddProjectResponse> addProject(@RequestBody @Validated final AddProjectRequest addProjectRequest) {
        return new ResponseEntity<>(new AddProjectResponse(projectService.addProject(addProjectRequest)), HttpStatus.CREATED);
    }

    /**
     *
     * @param projectId id of project
     * @return message
     */
    @DeleteMapping("{projectId}/deleteProject")
    @ResponseBody
    public ResponseEntity<?> deleteProject(@PathVariable @NotNull final UUID projectId) {
        try {
            projectService.deleteProject(projectId);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param projectId id of project
     * @return project
     */
    @GetMapping("{projectId}/getProject")
    @ResponseBody
    public ResponseEntity<?> getProject(@PathVariable @NotNull final UUID projectId) {
        try {
            return new ResponseEntity<>(projectService.getProjectById(projectId), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
