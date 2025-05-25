package core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.Suite;
import ru.omsu.core.model.AllSuitesInProject;
import ru.omsu.core.repository.tree.ITreeRepository;
import ru.omsu.core.service.tree.TreeService;
import ru.omsu.web.model.response.OneLevelResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TreeServiceTest {

    private ITreeRepository treeRepository;
    private TreeService treeService;

    @BeforeEach
    void setUp() {
        treeRepository = mock(ITreeRepository.class);
        treeService = new TreeService(treeRepository);
    }

    @Test
    void testGetOneLevel_withChildSuites() {
        UUID suiteId = UUID.randomUUID();
        int offset = 0;
        int limit = 10;

        Suite parentSuite = new Suite("Parent",suiteId,  UUID.randomUUID());
        Suite childSuite = new Suite("Child",UUID.randomUUID(),  suiteId);

        when(treeRepository.getOneLevelSuites(suiteId, offset, limit)).thenReturn(List.of(parentSuite));
        when(treeRepository.getOneLevelSuites(parentSuite.getSuiteId(), offset, limit)).thenReturn(List.of(childSuite));

        List<CaseDTO> testCases = new ArrayList<>();
        when(treeRepository.getOneLevelCases(suiteId, offset, limit)).thenReturn(testCases);

        OneLevelResponse response = treeService.getOneLevel(suiteId, offset, limit);

        assertNotNull(response);
        assertEquals(1, response.suites().size());
        assertFalse(response.suites().get(0).isHasChildSuites());
        assertEquals(testCases, response.cases());

        verify(treeRepository).getOneLevelSuites(suiteId, offset, limit);
        verify(treeRepository).getOneLevelSuites(parentSuite.getSuiteId(), offset, limit);
        verify(treeRepository).getOneLevelCases(suiteId, offset, limit);
    }

    @Test
    void testGetAllSuites_recursive() {
        UUID rootId = UUID.randomUUID();
        Suite suite1 = new Suite("Suite1",UUID.randomUUID(),  rootId);
        Suite suite2 = new Suite("Suite2",UUID.randomUUID(),  suite1.getSuiteId());

        // level 1
        when(treeRepository.getOneLevelSuites(rootId, 1, 100)).thenReturn(List.of(suite1));
        // level 2
        when(treeRepository.getOneLevelSuites(suite1.getSuiteId(), 1, 100)).thenReturn(List.of(suite2));
        // level 3 (leaf)
        when(treeRepository.getOneLevelSuites(suite2.getSuiteId(), 1, 100)).thenReturn(List.of());

        AllSuitesInProject result = treeService.getAllSuites(rootId);

        assertNotNull(result);
        assertEquals("Project Root", result.suiteName());
        assertEquals(1, result.children().size());
        assertEquals("Suite1", result.children().get(0).suiteName());
        assertEquals("Suite2", result.children().get(0).children().get(0).suiteName());

        verify(treeRepository, times(3)).getOneLevelSuites(any(), eq(1), eq(100));
    }
}
