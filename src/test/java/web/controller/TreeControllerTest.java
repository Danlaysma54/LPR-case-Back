package web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.omsu.core.model.AllSuitesInProject;
import ru.omsu.core.service.tree.ITreeService;
import ru.omsu.web.controllers.TreeController;
import ru.omsu.web.model.response.OneLevelResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TreeControllerTest {

    @Mock
    private ITreeService treeService;

    @InjectMocks
    private TreeController treeController;

    @Test
    void getFirstLevel_ShouldReturnOneLevelResponse() {
        // Arrange
        UUID projectId = UUID.randomUUID();
        UUID suiteId = UUID.randomUUID();
        int offset = 0;
        int limit = 10;
        OneLevelResponse expectedResponse = new OneLevelResponse(new ArrayList<>(),new ArrayList<>());

        when(treeService.getOneLevel(suiteId, offset, limit))
                .thenReturn(expectedResponse);

        // Act
        ResponseEntity<OneLevelResponse> response =
                treeController.getFirstLevel(projectId, suiteId, offset, limit);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(treeService).getOneLevel(suiteId, offset, limit);
    }

    @Test
    void getAllSuitesInProject_ShouldReturnListOfSuites() {
        // Arrange
        UUID projectId = UUID.randomUUID();
        AllSuitesInProject expectedSuites = Mockito.mock(AllSuitesInProject.class);
        when(treeService.getAllSuites(projectId))
                .thenReturn(expectedSuites);

        // Act
        ResponseEntity<?> response =
                treeController.getAllSuitesInProject(projectId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedSuites, response.getBody());
        verify(treeService).getAllSuites(projectId);
    }

    @Test
    void getFirstLevel_ShouldValidateParameters() {
        // Test with valid parameters
        assertDoesNotThrow(() -> {
            treeController.getFirstLevel(
                    UUID.randomUUID(),
                    UUID.randomUUID(),
                    0,
                    10
            );
        });
    }

    @Test
    void getAllSuitesInProject_ShouldValidatePathVariables() {
        // Test with valid UUID
        assertDoesNotThrow(() -> {
            treeController.getAllSuitesInProject(UUID.randomUUID());
        });
    }

    @Test
    void getFirstLevel_ShouldHandleServiceExceptions() {
        // Arrange
        UUID projectId = UUID.randomUUID();
        UUID suiteId = UUID.randomUUID();

        when(treeService.getOneLevel(any(), anyInt(), anyInt()))
                .thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            treeController.getFirstLevel(projectId, suiteId, 0, 10);
        });
    }
}