package core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.omsu.core.model.TestRun;
import ru.omsu.core.model.TestRunDTO;
import ru.omsu.core.repository.testRun.ITestRunRepository;
import ru.omsu.core.service.testRun.TestRunService;
import ru.omsu.web.model.request.AddTestRunRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.GetTestRunResponse;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestRunServiceTest {

    private ITestRunRepository testRunRepository;
    private TestRunService testRunService;

    @BeforeEach
    void setUp() {
        testRunRepository = mock(ITestRunRepository.class);
        testRunService = new TestRunService(testRunRepository);
    }

    @Test
    void testGetTestRuns() {
        List<TestRunDTO> testRuns = List.of(mock(TestRunDTO.class));
        when(testRunRepository.getTestRun()).thenReturn(testRuns);

        GetTestRunResponse response = testRunService.getTestRuns(UUID.randomUUID());

        assertNotNull(response);
        assertEquals(testRuns, response.testRunDTOList());
        verify(testRunRepository).getTestRun();
    }

    @Test
    void testAddTestRun() {
        AddTestRunRequest request = mock(AddTestRunRequest.class);
        UUID runId = UUID.randomUUID();

        when(testRunRepository.addTestRun(request)).thenReturn(runId);

        AddedEntityResponse response = testRunService.addTestRun(request);

        assertNotNull(response);
        assertEquals(runId, response.addedEntityId());
        verify(testRunRepository).addTestRun(request);
    }

    @Test
    void testEditTestRun() {
        TestRun run = mock(TestRun.class);
        UUID runId = UUID.randomUUID();
        when(run.testRunId()).thenReturn(runId);
        TestRun updatedRun = mock(TestRun.class);

        when(testRunRepository.getTestRunById(runId)).thenReturn(updatedRun);

        TestRun result = testRunService.editTestRun(run);

        verify(testRunRepository).editTestRun(run);
        verify(testRunRepository).getTestRunById(runId);
        assertEquals(updatedRun, result);
    }

    @Test
    void testDeleteTestRun() {
        UUID runId = UUID.randomUUID();

        testRunService.deleteTestRun(runId);

        verify(testRunRepository).deleteTestRun(runId);
    }
}
