package ru.omsu.core.service.suite;

import ru.omsu.core.model.Suite;
import ru.omsu.web.model.request.AddSuiteRequest;

public interface ISuiteService {
    Suite addSuite(AddSuiteRequest suiteRequest);
}
