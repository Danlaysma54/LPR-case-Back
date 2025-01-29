package ru.omsu.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.omsu.core.service.testPlan.ITestPlanService;
import ru.omsu.web.model.request.AddTestPlanRequest;


import java.util.List;
import java.util.UUID;

/**
 * class controller for test plans
 */
@RestController
@RequestMapping("{projectId}")
public class TestPlanController {
    private final ITestPlanService testPlanService;

    /**
     * @param testPlanService test plan service
     */
    public TestPlanController(final ITestPlanService testPlanService) {
        this.testPlanService = testPlanService;
    }

    /**
     * @param testPlanRequest test plan request
     * @return id of added test plan
     */
    @PostMapping("/addTestPlan")
    @ResponseBody
    public ResponseEntity<?> addTestPlan(final @RequestBody AddTestPlanRequest testPlanRequest) {
        return new ResponseEntity<>(testPlanService.addTestPlan(testPlanRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getTestPlans")
    @ResponseBody
    public ResponseEntity<?> getTestPlans(@RequestParam("projectId") final UUID projectId,@RequestParam("offset") final int offset,
                                          @RequestParam("limit") final int limit) {
        return new ResponseEntity<>(testPlanService.getTestPlansInProject(projectId, offset, limit), HttpStatus.OK);
    }
}
