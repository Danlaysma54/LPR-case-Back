package ru.omsu.core.service.tree;


import ru.omsu.core.model.Suite;
import ru.omsu.core.model.AllSuitesInProject;
import ru.omsu.core.model.SuiteDTO;
import ru.omsu.core.repository.tree.ITreeRepository;
import org.springframework.stereotype.Service;
import ru.omsu.web.model.response.OneLevelResponse;


import java.util.ArrayList;
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
        List<SuiteDTO> suites = treeRepository.getOneLevelSuites(suiteId, offset, limit);
        for (SuiteDTO suite : suites) {
            if (!treeRepository.getOneLevelSuites(suite.suiteId(), offset, limit).isEmpty()) {

            }
        }
        return new OneLevelResponse(
                treeRepository.getOneLevelCases(suiteId, offset, limit),
                suites
        );
    }

    @Override
    public AllSuitesInProject getAllSuites(UUID suiteId) {
        AllSuitesInProject AllSuitesInProject = new AllSuitesInProject("Project Root", suiteId, new ArrayList<>());
        insertSuites(suiteId, AllSuitesInProject);
        return AllSuitesInProject;
    }

    private void insertSuites(final UUID suiteId, AllSuitesInProject AllSuitesInProject) {
        List<Suite> suites = treeRepository.getOneLevelSuites(suiteId, 1, 100);
        if (!suites.isEmpty()) {
            for (Suite suite : suites) {
                AllSuitesInProject childSuite = new AllSuitesInProject(suite.getSuiteName(), suite.getSuiteId(), new ArrayList<>());
                AllSuitesInProject.children().add(childSuite);
                insertSuites(suite.getSuiteId(), childSuite);
            }
        } else {
            return;
        }
    }
}
