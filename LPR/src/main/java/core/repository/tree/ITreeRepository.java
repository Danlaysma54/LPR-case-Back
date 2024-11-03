package core.repository.tree;

import core.model.CaseAndSuiteResponse;
import core.model.CaseDTO;
import core.model.Suite;


import java.util.List;
import java.util.UUID;

public interface ITreeRepository {
    List<Suite> getOneLevelSuites(UUID suiteID);

    List<CaseDTO> getOneLevelCases(UUID suiteID);
}
