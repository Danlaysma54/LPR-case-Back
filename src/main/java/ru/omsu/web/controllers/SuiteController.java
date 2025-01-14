package ru.omsu.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.omsu.core.model.Suite;
import ru.omsu.core.service.suite.ISuiteService;
import ru.omsu.web.model.request.AddSuiteRequest;

@RestController
@RequestMapping("{projectId}")
public class SuiteController {
    private final ISuiteService suiteService;

    public SuiteController(final ISuiteService suiteService) {
        this.suiteService = suiteService;
    }

    @PostMapping("/addSuite")
    @ResponseBody
    public ResponseEntity<Suite> addSuite(@RequestBody AddSuiteRequest suiteRequest) {
        return new ResponseEntity<>(suiteService.addSuite(suiteRequest), HttpStatus.CREATED);
    }
}
