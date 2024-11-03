package core.service;

import core.model.CaseAndSuiteResponse;
import core.model.CaseDTO;
import core.model.Suite;
import core.repository.tree.ITreeRepository;
import core.service.tree.TreeService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import web.model.OneLevelResponse;

import java.util.ArrayList;
import java.util.List;
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
        ArrayList<CaseDTO> caseMock = new ArrayList<>();
        ArrayList<Suite> suiteMock = new ArrayList<>();
        when(treeRepository.getOneLevelSuites(projectId)).thenReturn(suiteMock);
        when(treeRepository.getOneLevelCases(projectId)).thenReturn(caseMock);
        OneLevelResponse oneLevelResponse= treeService.getOneLevel(projectId);
        verify(treeRepository,times(1)).getOneLevelCases(projectId);
        verify(treeRepository,times(1)).getOneLevelSuites(projectId);
        Assert.assertEquals(oneLevelResponse.getCases(),treeService.getOneLevel(projectId).getCases());
        Assert.assertEquals(oneLevelResponse.getSuites(),treeService.getOneLevel(projectId).getSuites());
    }
}
