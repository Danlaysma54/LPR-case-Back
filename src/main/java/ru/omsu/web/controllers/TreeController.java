package ru.omsu.web.controllers;


import ru.omsu.core.service.tree.ITreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
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
     * @return suites and cases
     */
    @GetMapping("/{suiteId}/getOneLevel")
    @ResponseBody
    public ResponseEntity<OneLevelResponse> getFirstLevel(@PathVariable("projectId") final UUID projectId,
                                                          @PathVariable("suiteId") final UUID suiteId) {

        return new ResponseEntity<>(treeService.getOneLevel(suiteId), HttpStatus.OK);
    }

}
