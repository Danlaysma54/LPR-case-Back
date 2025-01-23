package ru.omsu.core.service.tree;


import ru.omsu.core.model.Suite;
import ru.omsu.core.repository.tree.ITreeRepository;
import org.springframework.stereotype.Service;
import ru.omsu.web.model.response.OneLevelResponse;


import java.util.List;
import java.util.UUID;

/**
 * implementation class of tree service
 */
@Service
public class TreeService implements ITreeService {
    private final ITreeRepository treeRepository;

    /**
     * @param treeRepository repository of tree
     */
    public TreeService(final ITreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    /**
     * @param suiteId id of suite
     * @return object which consists of suites and cases
     */
    @Override
    public OneLevelResponse getOneLevel(final UUID suiteId, final int offset, final int limit) {
        List<Suite> suites = treeRepository.getOneLevelSuites(suiteId, offset, limit);
        for (Suite suite : suites) {
            if (!treeRepository.getOneLevelSuites(suite.getSuiteId(), offset, limit).isEmpty()) {
                suite.setHasChildSuites(true);
            }
        }
        return new OneLevelResponse(
                treeRepository.getOneLevelCases(suiteId, offset, limit),
                suites
        );
    }
}
