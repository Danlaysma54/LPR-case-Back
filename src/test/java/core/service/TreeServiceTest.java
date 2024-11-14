package core.service;

import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.Suite;
import ru.omsu.core.repository.tree.ITreeRepository;
import ru.omsu.core.service.tree.TreeService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.omsu.web.model.OneLevelResponse;

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
