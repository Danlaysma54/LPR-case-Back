package ru.omsu.core.service.suite;

import ru.omsu.core.model.Suite;
import ru.omsu.web.model.request.AddSuiteRequest;

import java.util.List;
import java.util.UUID;

/**
 * interface of suite service
 */
public interface ISuiteService {
    /**
     * @param suiteRequest object of adding request
     * @return ID of added suite
     */
    UUID addSuite(AddSuiteRequest suiteRequest);

    /**
     * @param suite new suite version
     * @return suite from db
     */
    Suite editSuite(Suite suite);

    /**
     * @param suiteId id of suite
     */
    void deleteSuite(UUID suiteId);

    /**
     *
     * @param projectId id of project
     * @return all suites, which are in project
     */
    List<Suite> getAllSuitesByProjectId(UUID projectId);
}
