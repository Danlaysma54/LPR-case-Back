package ru.omsu.core.repository.tree;

import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.Suite;


import java.util.List;
import java.util.UUID;

/**
 *
 */
public interface ITreeRepository {
    /**
     * @param suiteID id of suite
     * @param offset  number of page
     * @param limit   how many suites we can get
     * @return of suites
     */
    List<Suite> getOneLevelSuites(UUID suiteID, int offset, int limit);

    /**
     *
     * @param suiteID id of suite
     * @param offset number of page
     * @param limit how many suites we can get
     * @return cases
     */

    List<CaseDTO> getOneLevelCases(UUID suiteID, int offset, int limit);

    /**
     * @param projectID if of project
     * @return list of suite
     */

    List<Suite> getAllSuites(UUID projectID);
}
