package ru.omsu.core.service.tree;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.omsu.core.model.Project;
import ru.omsu.core.model.Suite;
import ru.omsu.core.model.SuiteDTO;
import ru.omsu.core.repository.project.ProjectRepository;
import ru.omsu.core.repository.suite.ISuiteRepository;
import ru.omsu.core.repository.suite.SuiteRepository;
import ru.omsu.core.repository.tree.ITreeRepository;
import org.springframework.stereotype.Service;
import ru.omsu.web.model.response.OneLevelResponse;


import java.util.UUID;

@Service
public class TreeService implements ITreeService {
    private final ITreeRepository treeRepository;
    private final ISuiteRepository suiteRepository;

    public TreeService(ITreeRepository treeRepository, ISuiteRepository suiteRepository) {
        this.treeRepository = treeRepository;
        this.suiteRepository = suiteRepository;
    }

    @Override
    public OneLevelResponse getOneLevel(UUID suiteId) {
        OneLevelResponse oneLevelResponse = new OneLevelResponse(
                treeRepository.getOneLevelCases(suiteId),
                treeRepository.getOneLevelSuites(suiteId),
                null,
                null
        );
        SuiteDTO suiteDTO = suiteRepository.getSuite(suiteId);
        if (suiteDTO != null) {
            oneLevelResponse.setSuiteName(suiteDTO.getSuiteName());
            oneLevelResponse.setSuiteId(suiteDTO.getSuiteId());
        }
        return oneLevelResponse;
    }
}
