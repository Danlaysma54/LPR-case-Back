package ru.omsu.core.service.tree;

import ru.omsu.web.model.response.OneLevelResponse;

import java.util.UUID;

/**
 * interface of tree service
 */
public interface ITreeService {
    /**
     * @param suiteId id of suite
     * @return object which consists of suites and cases
     */
    OneLevelResponse getOneLevel(UUID suiteId, int offset, int number);

}
