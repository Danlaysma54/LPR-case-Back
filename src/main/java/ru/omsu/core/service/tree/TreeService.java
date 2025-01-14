package ru.omsu.core.service.tree;



import ru.omsu.core.repository.tree.ITreeRepository;
import org.springframework.stereotype.Service;
import ru.omsu.web.model.response.OneLevelResponse;


import java.util.UUID;

/**
 * implementation class of tree service
 */
@Service
public class TreeService implements ITreeService {
    private final ITreeRepository treeRepository;

    /**
     *
     * @param treeRepository repository of tree
     */
    public TreeService(final ITreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    /**
     *
     * @param suiteId id of suite
     * @return object which consists of suites and cases
     */
    @Override
    public OneLevelResponse getOneLevel(UUID suiteId) {
        OneLevelResponse oneLevelResponse = new OneLevelResponse(
                treeRepository.getOneLevelCases(suiteId),
                treeRepository.getOneLevelSuites(suiteId),
                null,
                null
        );
        return oneLevelResponse;
    }
}
