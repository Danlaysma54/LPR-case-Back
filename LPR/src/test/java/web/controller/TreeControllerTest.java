package web.controller;

import core.model.CaseAndSuite;
import core.model.interfaces.Node;
import core.service.tree.ITreeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import web.controllers.TreeController;
import core.service.tree.TreeService;

import java.util.ArrayList;
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
        ArrayList<CaseAndSuite> node = new ArrayList<>();
        CaseAndSuite caseAndSuite = mock(CaseAndSuite.class);
        node.add(caseAndSuite);
        node.add(caseAndSuite);
        when(treeService.getFirstLevel(projectId)).thenReturn(node);
        ResponseEntity<ArrayList<CaseAndSuite>> response = treeController.getFirstLevel(projectId);
        verify(treeService, times(1)).getFirstLevel(projectId);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(node, response.getBody());
    }
}
