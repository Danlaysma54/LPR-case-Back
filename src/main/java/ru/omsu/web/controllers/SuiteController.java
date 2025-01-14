package ru.omsu.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.omsu.core.model.Suite;
import ru.omsu.core.service.suite.ISuiteService;
import ru.omsu.web.model.exception.RootIdNotExist;
import ru.omsu.web.model.request.AddSuiteRequest;
import ru.omsu.web.model.response.ErrorResponse;

@RestController
@RequestMapping("{projectId}")
public class SuiteController {
    private final ISuiteService suiteService;

    public SuiteController(final ISuiteService suiteService) {
        this.suiteService = suiteService;
    }

    @PostMapping("/addSuite")
    @ResponseBody
    public ResponseEntity<?> addSuite(@RequestBody AddSuiteRequest suiteRequest) {
        try {
            return new ResponseEntity<>(suiteService.addSuite(suiteRequest), HttpStatus.CREATED);
        } catch (RootIdNotExist e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }

    }
}
