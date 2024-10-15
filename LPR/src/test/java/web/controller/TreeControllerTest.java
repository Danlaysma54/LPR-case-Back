package web.controller;
import core.model.interfaces.Node;
import core.model.Suite;
import core.model.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import web.controllers.TreeController;
import web.service.TreeService;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
public class TreeControllerTest {
    private TreeController treeController;
    private TreeService treeService;
    @BeforeEach
    public void setup(){
        treeService = mock(TreeService.class);
        treeController = new TreeController(treeService);
    }
    @Test
    public void getFirstLevelTest(){
        ArrayList<Node> node = new ArrayList<Node>();
        Suite suite = mock(Suite.class);
        TestCase testCase = mock(TestCase.class);
        node.add(suite);
        node.add(testCase);
        when(treeService.getFirstLevel(projectId)).thenReturn();
    }
}
