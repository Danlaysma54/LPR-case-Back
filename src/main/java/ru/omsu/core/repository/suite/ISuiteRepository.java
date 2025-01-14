package ru.omsu.core.repository.suite;

import ru.omsu.core.model.Suite;
import ru.omsu.core.model.SuiteDTO;
import ru.omsu.core.model.TestCase;
import ru.omsu.web.model.request.AddSuiteRequest;
import ru.omsu.web.model.request.TestCaseRequest;

import java.util.UUID;

public interface ISuiteRepository {
    Suite getSuite(UUID suiteId);
    UUID addSuite(AddSuiteRequest addSuiteRequest);

}
