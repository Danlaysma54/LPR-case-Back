package core.service.tree;


import core.model.CaseAndSuite;
import core.repository.tree.ITreeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TreeService implements ITreeService {
    private final ITreeRepository treeRepository;

    public TreeService(ITreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    @Override
    public List<CaseAndSuite> getFirstLevel(UUID projectId) {
        List<CaseAndSuite> caseAndSuites = treeRepository.getFirstLevelCases(projectId);
        caseAndSuites.addAll(treeRepository.getFirstLevelSuites(projectId));
        return caseAndSuites;
    }
}
