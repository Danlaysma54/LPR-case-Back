package ru.omsu.web.model.exception;

/**
 * excpetion when root id doesn't exist
 */
public class RootIdNotExist extends RuntimeException {
    /**
     *
     * @param message of exception
     */
    public RootIdNotExist(final String message) {
        super(message);
    }
}
