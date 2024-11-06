package core.service;

import core.model.CaseDTO;
import core.model.Suite;
import core.model.TestCase;
import core.repository.testCase.ITestCaseRepository;
import core.repository.testCase.TestCaseRepository;
import core.repository.tree.ITreeRepository;
import core.service.testCase.TestCaseService;
import core.service.tree.TreeService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import web.model.OneLevelResponse;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestCaseServiceTest {
    private ITestCaseRepository testCaseRepository;
    private TestCaseService testCaseService;
    @BeforeEach
    public void setUp(){
        testCaseRepository =mock(ITestCaseRepository.class);
        testCaseService=new TestCaseService(testCaseRepository);

    }
    @Test
    public void getFirstLevelTestService(){
        UUID testCaseId = mock(UUID.class);
        TestCase testCaseMock = mock(TestCase.class);
        when(testCaseRepository.getTestCase(testCaseId)).thenReturn(testCaseMock);
        when(testCaseRepository.addTestCase(testCaseMock)).thenReturn(testCaseId);
        TestCase caseResponse= testCaseService.addTestCase(testCaseMock);
        verify(testCaseRepository,times(1)).addTestCase(testCaseMock);
        verify(testCaseRepository,times(1)).getTestCase(testCaseId);
        Assert.assertEquals(testCaseMock,caseResponse);
    }
}
