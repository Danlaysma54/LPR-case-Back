package web.controller;

import ru.omsu.core.model.TestCase;
import ru.omsu.core.service.testCase.ITestCaseService;
import ru.omsu.core.service.testCase.TestCaseService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import ru.omsu.web.controllers.TestCaseController;

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
