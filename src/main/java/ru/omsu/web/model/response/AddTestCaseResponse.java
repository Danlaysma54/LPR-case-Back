package ru.omsu.web.model.response;

import java.util.UUID;

public class AddTestCaseResponse {
    private final UUID addedTestCase;

    public AddTestCaseResponse(UUID addedTestCase) {
        this.addedTestCase = addedTestCase;
    }

    public UUID getAddedTestCase() {
        return addedTestCase;
    }
}
