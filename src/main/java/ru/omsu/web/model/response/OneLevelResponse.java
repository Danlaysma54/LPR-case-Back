package ru.omsu.web.model.response;

import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.Suite;

import java.util.List;
import java.util.UUID;

public class OneLevelResponse {
    List<CaseDTO> cases;
    List<Suite> suites;
    String suiteName;
    UUID suiteId;

    public OneLevelResponse(List<CaseDTO> cases, List<Suite> suites, String suiteName, UUID suiteId) {
        this.cases = cases;
        this.suites = suites;
        this.suiteName = suiteName;
        this.suiteId = suiteId;
    }

    public List<CaseDTO> getCases() {
        return cases;
    }

    public List<Suite> getSuites() {
        return suites;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public UUID getSuiteId() {
        return suiteId;
    }
}
