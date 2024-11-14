package ru.omsu.core.repository.tree;

import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.Suite;


import java.util.List;
import java.util.UUID;

public interface ITreeRepository {
    List<Suite> getOneLevelSuites(UUID suiteID);

    List<CaseDTO> getOneLevelCases(UUID suiteID);
}
