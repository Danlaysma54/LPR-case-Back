package ru.omsu.web.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * excpetion when root id doesn't exist
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdNotExist extends RuntimeException {
    /**
     *
     * @param message of exception
     */
    public IdNotExist(final String message) {
        super(message);
    }
}
