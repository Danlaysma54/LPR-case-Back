package ru.omsu.core.service.suite;
import ru.omsu.core.repository.suite.ISuiteRepository;
import ru.omsu.web.model.exception.IdNotExist;
import ru.omsu.web.model.request.AddSuiteRequest;

import java.util.UUID;

/**
 * implementation class of suite service
 */
public class SuiteService implements ISuiteService {
    private final ISuiteRepository suiteRepository;

    /**
     *
     * @param suiteRepository object of suite repo
     */
    public SuiteService(final ISuiteRepository suiteRepository) {
        this.suiteRepository = suiteRepository;
    }

    /**
     *
     * @param suiteRequest object of adding request
     * @return Suite object
     * @throws IdNotExist if root id doesn't exist
     */
    @Override
    public UUID addSuite(final AddSuiteRequest suiteRequest) throws IdNotExist {
        suiteRepository.getSuite(suiteRequest.getSuiteRootId());
        return suiteRepository.addSuite(suiteRequest);
    }
}
