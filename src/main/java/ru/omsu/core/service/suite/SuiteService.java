package ru.omsu.core.service.suite;

import ru.omsu.core.model.Suite;
import ru.omsu.core.repository.suite.ISuiteRepository;
import ru.omsu.core.repository.suite.SuiteRepository;
import ru.omsu.web.model.request.AddSuiteRequest;

public class SuiteService implements ISuiteService {
    private final ISuiteRepository suiteRepository;

    public SuiteService(ISuiteRepository suiteRepository) {
        this.suiteRepository = suiteRepository;
    }

    @Override
    public Suite addSuite(AddSuiteRequest suiteRequest) {
        return suiteRepository.getSuite(suiteRepository.addSuite(suiteRequest));
    }
}
