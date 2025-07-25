package ru.omsu.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.omsu.core.model.TestRun;
import ru.omsu.core.service.testRun.ITestRunService;
import ru.omsu.web.model.exception.IdNotExist;
import ru.omsu.web.model.request.AddTestRunRequest;


import java.util.UUID;

@Controller
@RequestMapping("{projectId}")
public class TestRunController {

    private final ITestRunService testRunService;

    public TestRunController(ITestRunService testRunService) {
        this.testRunService = testRunService;
    }

    @GetMapping
    @RequestMapping("/getTestRuns")
    public ResponseEntity<?> getTestRuns(@Validated @PathVariable("projectId") UUID projectId) {
        return new ResponseEntity<>(testRunService.getTestRuns(projectId), HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("/addTestRun")
    public ResponseEntity<?> addTestRun(@Validated @RequestBody AddTestRunRequest addTestRunRequest) {
        try {
            return new ResponseEntity<>(testRunService.addTestRun(addTestRunRequest), HttpStatus.CREATED);

        } catch (IdNotExist e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    @RequestMapping("/editTestRun")
    public ResponseEntity<?> editTestRun(@Validated @RequestBody TestRun testRun) {
        return new ResponseEntity<>(testRunService.editTestRun(testRun), HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping("/{testRunId}/deleteTestRun")
    public ResponseEntity<?> deleteTestRun(@Validated @PathVariable("testRunId") UUID testRunId) {
        testRunService.deleteTestRun(testRunId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
