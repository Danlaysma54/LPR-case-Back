package ru.omsu.core.service.suite;

import ru.omsu.core.model.Suite;
import ru.omsu.web.model.request.AddSuiteRequest;

/**
 *  interface of suite service
 */
public interface ISuiteService {
    /**
     *
     * @param suiteRequest object of adding request
     * @return Suite
     */
    Suite addSuite(AddSuiteRequest suiteRequest);
}
