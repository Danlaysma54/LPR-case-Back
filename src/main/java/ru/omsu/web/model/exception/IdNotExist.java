package ru.omsu.web.model.exception;

/**
 * excpetion when root id doesn't exist
 */
public class IdNotExist extends RuntimeException {
    /**
     *
     * @param message of exception
     */
    public IdNotExist(final String message) {
        super(message);
    }
}
