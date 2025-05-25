package core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.omsu.core.model.Automation;
import ru.omsu.core.model.Layer;
import ru.omsu.core.model.Step;
import ru.omsu.core.model.TestCase;
import ru.omsu.core.repository.testCase.ITestCaseRepository;
import ru.omsu.core.service.testCase.TestCaseService;
import ru.omsu.web.model.request.StepsRequest;
import ru.omsu.web.model.request.TestCaseRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.TestCaseTypes;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestCaseServiceTest {

    private ITestCaseRepository testCaseRepository;
    private TestCaseService testCaseService;

    @BeforeEach
    void setUp() {
        testCaseRepository = mock(ITestCaseRepository.class);
        testCaseService = new TestCaseService(testCaseRepository);
    }

    @Test
    void testAddTestCase_success() {
        UUID testCaseId = UUID.randomUUID();

        StepsRequest step1 = mock(StepsRequest.class);
        StepsRequest step2 = mock(StepsRequest.class);
        TestCaseRequest request = mock(TestCaseRequest.class);

        when(request.steps()).thenReturn(List.of(step1, step2));
        when(testCaseRepository.addTestCase(request)).thenReturn(testCaseId);

        AddedEntityResponse response = testCaseService.addTestCase(request);

        assertEquals(testCaseId, response.addedEntityId());
        verify(testCaseRepository).addTestCase(request);
        verify(testCaseRepository).addTestSteps(step1, testCaseId);
        verify(testCaseRepository).addTestSteps(step2, testCaseId);
    }

    @Test
    void testDeleteTestCase() {
        UUID testCaseId = UUID.randomUUID();

        doNothing().when(testCaseRepository).deleteTestCase(testCaseId);

        testCaseService.deleteTestCase(testCaseId);

        verify(testCaseRepository).deleteTestCase(testCaseId);
    }

    @Test
    void testEditTestCase() {
        UUID testCaseId = UUID.randomUUID();
        TestCase input = mock(TestCase.class);
        TestCase output = mock(TestCase.class);

        when(input.getTestCaseId()).thenReturn(testCaseId);
        doNothing().when(testCaseRepository).editTestCase(input);
        when(testCaseRepository.getTestCase(testCaseId)).thenReturn(output);

        TestCase result = testCaseService.editTestCase(input);

        assertEquals(output, result);
        verify(testCaseRepository).editTestCase(input);
        verify(testCaseRepository).getTestCase(testCaseId);
    }

    @Test
    void testGetTestCase() {
        UUID testCaseId = UUID.randomUUID();
        TestCase testCase = mock(TestCase.class);
        List<Step> steps = List.of(mock(Step.class), mock(Step.class));

        when(testCaseRepository.getTestCase(testCaseId)).thenReturn(testCase);
        when(testCaseRepository.getTestCaseSteps(testCaseId)).thenReturn(steps);

        TestCase result = testCaseService.getTestCase(testCaseId);

        assertEquals(testCase, result);
        verify(testCaseRepository).getTestCase(testCaseId);
        verify(testCaseRepository).getTestCaseSteps(testCaseId);
    }

    @Test
    void testGetTestCaseTypes() {
        List<Layer> layers = List.of(mock(Layer.class), mock(Layer.class));
        List<Automation> automation = List.of(mock(Automation.class), mock(Automation.class));

        when(testCaseRepository.getTestCaseLayers()).thenReturn(layers);
        when(testCaseRepository.getTestCaseAutomation()).thenReturn(automation);

        TestCaseTypes result = testCaseService.getTestCaseTypes();

        assertEquals(layers, result.getLayers());
        assertEquals(automation, result.getAutomations());
        verify(testCaseRepository).getTestCaseLayers();
        verify(testCaseRepository).getTestCaseAutomation();
    }
}
