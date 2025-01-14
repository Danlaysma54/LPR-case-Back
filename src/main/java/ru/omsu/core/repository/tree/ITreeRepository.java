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
     *
     * @param suiteID id of suite root
     * @return list of suite
     */
    List<Suite> getOneLevelSuites(UUID suiteID);

    /**
     *
     * @param suiteID id of suite root
     * @return list of cases
     */

    List<CaseDTO> getOneLevelCases(UUID suiteID);

    /**
     *
     * @param projectID if of project
     * @return list of suite
     */

    List<Suite> getAllSuites(UUID projectID);
}
