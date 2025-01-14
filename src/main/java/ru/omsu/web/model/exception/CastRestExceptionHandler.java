package ru.omsu.web.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice(basePackages = {"ru.omsu"})
public class CastRestExceptionHandler {
    @ExceptionHandler({MethodArgumentTypeMismatchException .class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(new CastException("Error with casting string to UUID"),
                HttpStatus.BAD_REQUEST);
    }
}
