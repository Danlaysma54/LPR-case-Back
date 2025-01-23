package ru.omsu.web.controllers;

import org.springframework.web.bind.annotation.*;
import ru.omsu.core.model.TestCase;
import ru.omsu.core.service.testCase.ITestCaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.omsu.web.model.exception.IdNotExist;
import ru.omsu.web.model.request.TestCaseRequest;

import java.util.UUID;

/**
 *
 */
@RestController
@RequestMapping("{projectId}")
public class TestCaseController {
    private final ITestCaseService testCaseService;

    /**
     * @param testCaseService class for test case service
     */
    public TestCaseController(final ITestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    /**
     * @param testCaseRequest request object to add test case
     * @return test case
     */
    @PostMapping("/addTestCase")
    @ResponseBody
    public ResponseEntity<UUID> addTestCase(@RequestBody final TestCaseRequest testCaseRequest) {
        return new ResponseEntity<>(testCaseService.addTestCase(testCaseRequest), HttpStatus.CREATED);
    }

    /**
     * @param testCaseId id of test case
     * @return message
     */
    @DeleteMapping("/deleteTestCase")
    @ResponseBody
    public ResponseEntity<?> deleteTestCase(@PathVariable final UUID testCaseId) {
        try {
            testCaseService.deleteTestCase(testCaseId);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @param testCase test case object
     * @return new test case
     */
    @PatchMapping("/editTestCase")
    @ResponseBody
    public ResponseEntity<?> editTestCase(@RequestBody final TestCase testCase) {
        try {
            return new ResponseEntity<>(testCaseService.editTestCase(testCase), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{testCaseId}/getTestCase")
    @ResponseBody
    public ResponseEntity<?> getTestCase(@PathVariable final UUID projectId, @PathVariable final UUID testCaseId) {
        try {
            return new ResponseEntity<>(testCaseService.getTestCase(testCaseId), HttpStatus.OK);
        } catch (IdNotExist e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
