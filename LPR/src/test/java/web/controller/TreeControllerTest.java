package web.controller;

import core.service.tree.ITreeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import web.controllers.TreeController;
import core.service.tree.TreeService;
import web.model.OneLevelResponse;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TreeControllerTest {
    private TreeController treeController;
    private ITreeService treeService;

    @BeforeEach
    public void setup() {
        treeService = mock(TreeService.class);
        treeController = new TreeController(treeService);
    }

    @Test
    public void getFirstLevelTest() {
        UUID projectId = mock(UUID.class);
        OneLevelResponse mockResp = mock(OneLevelResponse.class);
        when(treeService.getOneLevel(projectId)).thenReturn(mockResp);
        ResponseEntity<OneLevelResponse> response = treeController.getFirstLevel(projectId);
        verify(treeService, times(1)).getOneLevel(projectId);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(mockResp, response.getBody());
    }
}
