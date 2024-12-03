package ru.omsu.web.controllers;


import ru.omsu.core.model.Suite;
import ru.omsu.core.service.tree.ITreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.omsu.web.model.response.OneLevelResponse;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/{projectId}")
public class TreeController {
    private final ITreeService treeService;

    public TreeController(final ITreeService treeService) {
        this.treeService = treeService;
    }

    @GetMapping("/{suiteId}/getOneLevel")
    @ResponseBody
    public ResponseEntity<OneLevelResponse> getFirstLevel(@PathVariable("projectId") final UUID projectId, @PathVariable("suiteId") final UUID suiteId) {
        return new ResponseEntity<>(treeService.getOneLevel(suiteId), HttpStatus.OK);
    }

}
