package ru.omsu.web.controllers;

import jakarta.validation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.omsu.core.model.Suite;
import ru.omsu.core.service.suite.ISuiteService;
import ru.omsu.web.model.exception.IdNotExist;
import ru.omsu.web.model.request.AddSuiteRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.ErrorResponse;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * class controller for suite
 */
@RestController
@RequestMapping("{projectId}")
public class SuiteController {
    private final ISuiteService suiteService;
    private final Validator validator;

    /**
     * @param suiteService service suite
     */
    public SuiteController(final ISuiteService suiteService) {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            this.suiteService = suiteService;
            validator = factory.getValidator();
        }
    }

    /**
     * @param suiteRequest suite request
     * @return message
     */
    @PostMapping("/addSuite")
    @ResponseBody
    public ResponseEntity<?> addSuite(@Validated @RequestBody final AddSuiteRequest suiteRequest) {
        try {
            Set<ConstraintViolation<AddSuiteRequest>> violations = validator.validate(suiteRequest);
            if (!violations.isEmpty()) {
                return new ResponseEntity<>(new ErrorResponse(
                        violations
                                .stream()
                                .map(x -> x.getMessageTemplate())
                                .collect(Collectors.joining(","))), HttpStatus.BAD_REQUEST);

            }
            return new ResponseEntity<>(new AddedEntityResponse(suiteService.addSuite(suiteRequest)), HttpStatus.CREATED);
        } catch (IdNotExist e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param suite with new data of suite
     * @return new
     */
    @PatchMapping("/editSuite")
    @ResponseBody
    public ResponseEntity<?> editSuite(@Validated @RequestBody final Suite suite) {
        try {
            Set<ConstraintViolation<Suite>> violations = validator.validate(suite);
            if (!violations.isEmpty()) {
                return new ResponseEntity<>(new ErrorResponse(
                        violations
                                .stream()
                                .map(x -> x.getMessageTemplate())
                                .collect(Collectors.joining(","))), HttpStatus.BAD_REQUEST);

            }
            return new ResponseEntity<Suite>(suiteService.editSuite(suite), HttpStatus.OK);
        } catch (IdNotExist e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param suiteId   id of suite
     * @return empty response if entity is deleted and messages with errors if not
     */
    @DeleteMapping("/{suiteId}/deleteSuite")
    @ResponseBody
    public ResponseEntity<?> deleteSuite(
                                        @Validated @PathVariable("suiteId") final UUID suiteId) {
        try {
            suiteService.deleteSuite(suiteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IdNotExist e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
