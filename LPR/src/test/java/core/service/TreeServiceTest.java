package core.service;

import core.model.CaseAndSuite;
import core.repository.tree.ITreeRepository;
import core.service.tree.TreeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class TreeServiceTest {
    private ITreeRepository treeRepository;
    private TreeService treeService;
    @BeforeEach
    public void setUp(){
        treeRepository =mock(ITreeRepository.class);
        treeService=new TreeService(treeRepository);

    }
    @Test
    public void getFirstLevelTestService(){
        UUID projectId = mock(UUID.class);
        ArrayList<CaseAndSuite> node = new ArrayList<>();
        CaseAndSuite caseAndSuite = mock(CaseAndSuite.class);
        node.add(caseAndSuite);
        node.add(caseAndSuite);
        when(treeRepository.getFirstLevel(projectId)).thenReturn(node);
        ArrayList<CaseAndSuite> response= treeService.getFirstLevel(projectId);
        verify(treeRepository,times(1)).getFirstLevel(projectId);
        Assertions.assertEquals(node, response);
    }
}
