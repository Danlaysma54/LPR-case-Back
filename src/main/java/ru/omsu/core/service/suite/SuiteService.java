package ru.omsu.core.service.suite;

import ru.omsu.core.model.Suite;
import ru.omsu.core.repository.suite.ISuiteRepository;
import ru.omsu.web.model.exception.RootIdNotExist;
import ru.omsu.web.model.request.AddSuiteRequest;

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
     * @throws RootIdNotExist if root id doesn't exist
     */
    @Override
    public Suite addSuite(final AddSuiteRequest suiteRequest) throws RootIdNotExist {
        suiteRepository.getSuite(suiteRequest.getSuiteRootId());
        return suiteRepository.getSuite(suiteRepository.addSuite(suiteRequest));
    }
}
