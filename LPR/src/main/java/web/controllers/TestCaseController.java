package web.controllers;

import core.model.TestCase;
import core.service.testCase.ITestCaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("{projectId}")
public class TestCaseController {
    private final ITestCaseService testCaseService;

    public TestCaseController(final ITestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }
    @PostMapping("/addTestCase")
    @ResponseBody
    public ResponseEntity<TestCase> addTestCase(@RequestBody TestCase testCase){
        return new ResponseEntity<>(testCaseService.addTestCase(testCase), HttpStatus.CREATED);
    }
}
