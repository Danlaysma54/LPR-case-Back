package ru.omsu.core.repository.suite;

import ru.omsu.core.model.Suite;
import ru.omsu.web.model.request.AddSuiteRequest;

import java.util.List;
import java.util.UUID;

/**
 * interface for requests to db on suite entity
 */
public interface ISuiteRepository {
    /**
     * @param suiteId id of suite
     * @return suite entity
     */
    Suite getSuite(UUID suiteId);

    /**
     * @param addSuiteRequest request entity to add suite
     * @return UUID of added suite
     */
    UUID addSuite(AddSuiteRequest addSuiteRequest);

    /**
     * @param suite new version of suite
     */
    void editSuite(Suite suite);

    /**
     * @param suiteId id of suite
     */
    void deleteSuite(UUID suiteId);

    /**
     *
     * @param projectId id of project
     * @return list of suite
     */
    List<UUID> getAllSuitesInProject(UUID projectId);
}
