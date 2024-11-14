package ru.omsu.web.model;

import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.Suite;

import java.util.List;

public class OneLevelResponse {
    List<CaseDTO> cases;
    List<Suite> suites;

    public OneLevelResponse(List<CaseDTO> cases, List<Suite> suites) {
        this.cases = cases;
        this.suites = suites;
    }

    public List<CaseDTO> getCases() {
        return cases;
    }

    public List<Suite> getSuites() {
        return suites;
    }
}
