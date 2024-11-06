package web.controller;

import core.model.TestCase;
import core.repository.testCase.ITestCaseRepository;
import core.service.testCase.ITestCaseService;
import core.service.testCase.TestCaseService;
import core.service.tree.ITreeService;
import core.service.tree.TreeService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import web.controllers.TestCaseController;
import web.controllers.TreeController;
import web.model.OneLevelResponse;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestCaseControllerTest {
    private TestCaseController testCaseController;
    private ITestCaseService testCaseService;

    @BeforeEach
    public void setup() {
        testCaseService = mock(TestCaseService.class);
        testCaseController = new TestCaseController(testCaseService);
    }

    @Test
    public void addTestCase() {
        TestCase mockResp = mock(TestCase.class);
        when(testCaseService.addTestCase(mockResp)).thenReturn(mockResp);
        ResponseEntity<TestCase> answer = testCaseController.addTestCase(mockResp);
        verify(testCaseService, times(1)).addTestCase(mockResp);
        Assert.assertEquals(answer.getBody(),mockResp);
    }
}
