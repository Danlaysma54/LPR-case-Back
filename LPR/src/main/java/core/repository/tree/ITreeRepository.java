package core.repository.tree;

import core.model.CaseAndSuite;


import java.util.List;
import java.util.UUID;

public interface ITreeRepository {
    List<CaseAndSuite> getFirstLevelSuites(UUID projectID);

    List<CaseAndSuite> getFirstLevelCases(UUID projectID);
}
