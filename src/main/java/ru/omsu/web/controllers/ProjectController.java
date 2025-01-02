package ru.omsu.web.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.omsu.core.service.project.IProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.omsu.web.model.request.AddProjectRequest;
import ru.omsu.web.model.response.AddProjectResponse;

import java.util.UUID;

@RestController
@RequestMapping("/")
public class ProjectController {
    private final IProjectService projectService;

    public ProjectController(final IProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/addProject")
    @ResponseBody
    public ResponseEntity<AddProjectResponse> addProject(@RequestBody @Validated AddProjectRequest addProjectRequest) {
        return new ResponseEntity<>(new AddProjectResponse(projectService.addProject(addProjectRequest)), HttpStatus.CREATED);
    }

    @DeleteMapping("{projectId}/deleteProject")
    @ResponseBody
    public ResponseEntity<?> deleteProject(@PathVariable @NotNull UUID projectId) {
        try {
            projectService.deleteProject(projectId);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{projectId}/getProject")
    @ResponseBody
    public ResponseEntity<?> getProject(@PathVariable @NotNull UUID projectId) {
        try {
            return new ResponseEntity<>(projectService.getProjectById(projectId), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
