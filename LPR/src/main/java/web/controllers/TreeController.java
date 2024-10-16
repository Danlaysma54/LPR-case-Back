package web.controllers;

import core.model.CaseAndSuite;
import core.service.tree.ITreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;
@RestController
@RequestMapping("/{projectId}/tree")
public class TreeController {
    private final ITreeService treeService;

    public TreeController(final ITreeService treeService) {
        this.treeService = treeService;
    }
    @GetMapping("/getFirstLevel")
    @ResponseBody
    public ResponseEntity<ArrayList<CaseAndSuite>> getFirstLevel(@PathVariable("projectId") final UUID projectId){
      return new ResponseEntity<>(treeService.getFirstLevel(projectId), HttpStatus.OK);
    }
}
