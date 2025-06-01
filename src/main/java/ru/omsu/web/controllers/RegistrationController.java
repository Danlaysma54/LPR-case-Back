package ru.omsu.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omsu.core.service.registration.UserRegistrationService;
import ru.omsu.web.model.request.RegistrationRequestDto;
import ru.omsu.web.model.response.ErrorResponse;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;

    public RegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody final RegistrationRequestDto registrationDTO) {
        try {
            userRegistrationService.registerUser(registrationDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
