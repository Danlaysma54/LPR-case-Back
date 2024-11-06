package ru.omsu.core.service.tree;

import ru.omsu.core.repository.tree.ITreeRepository;
import org.springframework.stereotype.Service;
import ru.omsu.web.model.OneLevelResponse;


import java.util.UUID;

@Service
public class TreeService implements ITreeService {
    private final ITreeRepository treeRepository;

    public TreeService(ITreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    @Override
    public OneLevelResponse getOneLevel(UUID suiteId) {
        return new OneLevelResponse(treeRepository.getOneLevelCases(suiteId), treeRepository.getOneLevelSuites(suiteId));
    }
}
