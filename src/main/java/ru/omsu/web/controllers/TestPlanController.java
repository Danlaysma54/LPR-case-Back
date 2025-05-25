package ru.omsu.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.omsu.core.model.TestPlan;
import ru.omsu.core.service.testPlan.ITestPlanService;
import ru.omsu.web.model.request.EditTestPlanRequest;
import ru.omsu.web.model.request.TestPlanRequest;
import ru.omsu.web.model.response.GetTestPlanByIdResponse;

import java.util.UUID;

/**
 * class for controller for test plan entity
 */
@RestController
@RequestMapping("{projectId}")
public class TestPlanController {
    private final ITestPlanService testPlanService;

    public TestPlanController(ITestPlanService testPlanService) {
        this.testPlanService = testPlanService;
    }

    @GetMapping("/getTestPlans")
    @ResponseBody
    public ResponseEntity<?> getTestPlans(@Validated @PathVariable UUID projectId) {
        return new ResponseEntity<>(testPlanService.getTestPlans(projectId), HttpStatus.OK);
    }

    @PostMapping("/addTestPlan")
    @ResponseBody
    public ResponseEntity<?> addTestPlan(@Validated @PathVariable UUID projectId, @Validated @RequestBody TestPlanRequest testPlanRequest) {
        return new ResponseEntity<>(testPlanService.addTestPlan(testPlanRequest), HttpStatus.CREATED);
    }

        @DeleteMapping("/{testPlanId}/deleteTestPlan")
    @ResponseBody
    public ResponseEntity<?> deleteTestPlan(@Validated @PathVariable UUID testPlanId,@Validated @PathVariable UUID projectId) {
        testPlanService.deleteTestPlan(testPlanId, projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/editTestPlan")
    @ResponseBody
    public ResponseEntity<?> editTestPlan(@Validated @PathVariable UUID projectId,@Validated @RequestBody EditTestPlanRequest testPlan) {
        testPlanService.editTestPlan(testPlan);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/testPlans/{testPlanId}")
    @ResponseBody
    public ResponseEntity<?> getTestPlanById(@Validated @PathVariable UUID projectId, @Validated @PathVariable UUID testPlanId) {
        GetTestPlanByIdResponse testPlan = testPlanService.getTestPlanById(projectId, testPlanId);
        return new ResponseEntity<>(testPlan, HttpStatus.OK);
    }
}
