package ru.omsu.web.controllers;


import org.springframework.web.bind.annotation.*;
import ru.omsu.core.service.tree.ITreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.omsu.web.model.response.OneLevelResponse;


import java.util.UUID;

@RestController
@RequestMapping("/{projectId}")
public class TreeController {
    private final ITreeService treeService;

    /**
     * @param treeService service for tree entity
     */
    public TreeController(final ITreeService treeService) {
        this.treeService = treeService;
    }

    /**
     * @param projectId id of project
     * @param suiteId   id of suite
     * @param limit     numb of suites and cases
     * @param offset    number of page
     * @return suites and cases
     */
    @GetMapping("/{suiteId}/getOneLevel")
    @ResponseBody
    public ResponseEntity<OneLevelResponse> getFirstLevel(@PathVariable("projectId") final UUID projectId,
                                                          @PathVariable("suiteId") final UUID suiteId,
                                                          @RequestParam("offset") final int offset,
                                                          @RequestParam("limit") final int limit) {
        return new ResponseEntity<>(treeService.getOneLevel(suiteId, offset, limit), HttpStatus.OK);
    }

    @GetMapping("/getAllSuitesInProject")
    @ResponseBody
    public ResponseEntity<?> getTest(@PathVariable("projectId") final UUID projectId) {
        return new ResponseEntity<>(treeService.getAllSuites(projectId), HttpStatus.OK);
    }

}
