package ru.omsu.web.controllers;

import ru.omsu.core.model.TestCase;
import ru.omsu.core.service.testCase.ITestCaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.omsu.web.model.request.TestCaseRequest;

import java.util.UUID;

@RestController
@RequestMapping("{projectId}")
public class TestCaseController {
    private final ITestCaseService testCaseService;

    public TestCaseController(final ITestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    @PostMapping("/addTestCase")
    @ResponseBody
    public ResponseEntity<TestCase> addTestCase(@RequestBody TestCaseRequest testCaseRequest){
        return new ResponseEntity<>(testCaseService.addTestCase(testCaseRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{testCaseId}/deleteTestCase")
    @ResponseBody
    public ResponseEntity deleteTestCase(@PathVariable UUID testCaseId) {
        try {
            testCaseService.deleteTestCase(testCaseId);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @PatchMapping("/{testCaseId}/editTestCase")
    @ResponseBody
    public ResponseEntity editTestCase(@RequestBody TestCase testCase, @PathVariable("testCaseId") UUID testCaseId) {
        try {
            testCase.setTestCaseId(testCaseId);
            return new ResponseEntity<>(testCaseService.editTestCase(testCase), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
