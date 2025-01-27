package ru.omsu.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.omsu.core.service.testPlan.ITestPlanService;
import ru.omsu.web.model.request.AddTestPlanRequest;

@RestController
@RequestMapping("{projectId}")
public class TestPlanController {
    private final ITestPlanService testPlanService;

    public TestPlanController(ITestPlanService testPlanService) {
        this.testPlanService = testPlanService;
    }

    @PostMapping("/addTestPlan")
    @ResponseBody
    public ResponseEntity<?> addTestPlan(AddTestPlanRequest testPlanRequest) {
        return new ResponseEntity<>(testPlanService.addTestPlan(testPlanRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getTestPlans")
    @ResponseBody
    public ResponseEntity<?> getTestPlans() {
        return null;
    }
}
