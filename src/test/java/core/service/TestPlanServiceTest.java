package core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.TestPlanDTO;
import ru.omsu.core.model.TestPlanWithSuitesId;
import ru.omsu.core.repository.testPlan.ITestPlanRepository;
import ru.omsu.core.service.testPlan.TestPlanService;
import ru.omsu.web.model.exception.IdNotExist;
import ru.omsu.web.model.request.EditTestPlanRequest;
import ru.omsu.web.model.request.TestPlanRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.GetTestPlanByIdResponse;
import ru.omsu.web.model.response.GetTestPlansResponse;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestPlanServiceTest {

    private ITestPlanRepository testPlanRepository;
    private TestPlanService testPlanService;

    @BeforeEach
    void setUp() {
        testPlanRepository = mock(ITestPlanRepository.class);
        testPlanService = new TestPlanService(testPlanRepository);
    }

    @Test
    void testGetTestPlans() {
        UUID projectId = UUID.randomUUID();
        UUID planId = UUID.randomUUID();

        List<TestPlanDTO> dtos = List.of(new TestPlanDTO(planId, "Plan A"));
        List<CaseDTO> cases = List.of(new CaseDTO( "case1",UUID.randomUUID()));

        when(testPlanRepository.getTestPlans(projectId)).thenReturn(dtos);
        when(testPlanRepository.getCaseInTestPlans(planId)).thenReturn(cases);

        GetTestPlansResponse response = testPlanService.getTestPlans(projectId);

        assertEquals(1, response.getTestPlans().size());
        assertEquals("Plan A", response.getTestPlans().get(0).getTestPlanName());
        assertEquals(cases, response.getTestPlans().get(0).getTestCases());
    }

    @Test
    void testAddTestPlan() {
        UUID testPlanId = UUID.randomUUID();
        List<UUID> caseIds = List.of(UUID.randomUUID(), UUID.randomUUID());
        TestPlanRequest request = mock(TestPlanRequest.class);

        when(request.getTestCases()).thenReturn(caseIds);
        when(testPlanRepository.addTestPlan(request)).thenReturn(testPlanId);

        AddedEntityResponse response = testPlanService.addTestPlan(request);

        assertEquals(testPlanId, response.addedEntityId());
        verify(testPlanRepository).addTestPlan(request);
        for (UUID caseId : caseIds) {
            verify(testPlanRepository).addTestCaseInTestPlan(caseId, testPlanId);
        }
    }

    @Test
    void testDeleteTestPlan() {
        UUID testPlanId = UUID.randomUUID();
        UUID projectId = UUID.randomUUID();

        doNothing().when(testPlanRepository).deleteTestPlan(testPlanId);

        testPlanService.deleteTestPlan(testPlanId, projectId);

        verify(testPlanRepository).deleteTestPlan(testPlanId);
    }

    @Test
    void testEditTestPlan() {
        UUID testPlanId = UUID.randomUUID();
        List<UUID> caseIds = List.of(UUID.randomUUID(), UUID.randomUUID());

        EditTestPlanRequest request = mock(EditTestPlanRequest.class);
        when(request.getTestPlanId()).thenReturn(testPlanId);
        when(request.getTestCases()).thenReturn(caseIds);

        testPlanService.editTestPlan(request);

        verify(testPlanRepository).editTestPlanName(request);
        verify(testPlanRepository).deleteAllTestCasesInTestPlan(testPlanId);
        for (UUID caseId : caseIds) {
            verify(testPlanRepository).addTestCaseInTestPlan(caseId, testPlanId);
        }
    }

    @Test
    void testGetTestPlanById_success() throws IdNotExist {
        UUID projectId = UUID.randomUUID();
        UUID planId = UUID.randomUUID();
        TestPlanWithSuitesId mockData = mock(TestPlanWithSuitesId.class);

        when(testPlanRepository.getTestPlanById(projectId, planId)).thenReturn(mockData);

        GetTestPlanByIdResponse result = testPlanService.getTestPlanById(projectId, planId);

        assertNotNull(result);
        assertEquals(mockData, result.getTestPlan());
        verify(testPlanRepository).getTestPlanById(projectId, planId);
    }


    @Test
    void testGetTestPlanById_notFound() {
        UUID projectId = UUID.randomUUID();
        UUID planId = UUID.randomUUID();

        when(testPlanRepository.getTestPlanById(projectId, planId)).thenThrow(new IdNotExist("Not found"));

        IdNotExist thrown = assertThrows(IdNotExist.class, () ->
                testPlanService.getTestPlanById(projectId, planId)
        );

        assertEquals("Test plan with id " + planId + " not found", thrown.getMessage());
    }
}
