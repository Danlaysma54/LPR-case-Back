package ru.omsu.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import ru.omsu.core.service.suite.ISuiteService;
import ru.omsu.web.model.exception.IdNotExist;
import ru.omsu.web.model.request.AddSuiteRequest;
import ru.omsu.web.model.response.ErrorResponse;

/**
 *  class controller for suite
 */
@RestController
@RequestMapping("{projectId}")
public class SuiteController {
    private final ISuiteService suiteService;

    /**
     *
     * @param suiteService service suite
     */
    public SuiteController(final ISuiteService suiteService) {
        this.suiteService = suiteService;
    }

    /**
     *
     * @param suiteRequest suite request
     * @return message
     */
    @PostMapping("/addSuite")
    @ResponseBody
    public ResponseEntity<?> addSuite(@RequestBody final AddSuiteRequest suiteRequest) {
        try {
            return new ResponseEntity<>(suiteService.addSuite(suiteRequest), HttpStatus.CREATED);
        } catch (IdNotExist e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }

    }
}
