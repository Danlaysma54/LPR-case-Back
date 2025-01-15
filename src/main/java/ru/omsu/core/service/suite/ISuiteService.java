package ru.omsu.core.service.suite;

import ru.omsu.core.model.Suite;
import ru.omsu.web.model.request.AddSuiteRequest;

import java.util.UUID;

/**
 *  interface of suite service
 */
public interface ISuiteService {
    /**
     *
     * @param suiteRequest object of adding request
     * @return ID of added suite
     */
    UUID addSuite(AddSuiteRequest suiteRequest);
}
