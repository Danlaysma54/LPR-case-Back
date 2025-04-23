package ru.omsu.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.omsu.core.service.testRun.ITestRunService;

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
    public ResponseEntity<?>  getTestRuns(@PathVariable("projectId") UUID projectId){
        return null;
}
}
