package ru.omsu.core.service.testPlan;

import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.TestPlan;
import ru.omsu.core.model.TestPlanDTO;
import ru.omsu.core.repository.testPlan.ITestPlanRepository;
import ru.omsu.web.model.exception.IdNotExist;
import ru.omsu.web.model.request.TestPlanRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.GetTestPlanByIdResponse;
import ru.omsu.web.model.response.GetTestPlansResponse;

import java.util.*;

/**
 * test plan service
 */
public class TestPlanService implements ITestPlanService {
    private final ITestPlanRepository testPlanRepository;

    public TestPlanService(ITestPlanRepository testPlanRepository) {
        this.testPlanRepository = testPlanRepository;
    }


    @Override
    public GetTestPlansResponse getTestPlans(UUID projectId) {
        List<TestPlan> testPlans = new ArrayList<>();
        List<TestPlanDTO> testPlanDTOList = testPlanRepository.getTestPlans(projectId);
        for (TestPlanDTO testPlanDTO : testPlanDTOList) {
            testPlans.add(new TestPlan(testPlanDTO.getTestPlanId(), testPlanDTO.getTestPlanName(),
                    testPlanRepository.getCaseInTestPlans(testPlanDTO.getTestPlanId())));
        }
        return new GetTestPlansResponse(testPlans);
    }

    @Override
    public AddedEntityResponse addTestPlan(TestPlanRequest testPlanRequest) {
        UUID addedTestPlan = testPlanRepository.addTestPlan(testPlanRequest);
        addingTestCaseInTestPlan(testPlanRequest.getTestCases(), addedTestPlan);
        return new AddedEntityResponse(addedTestPlan);
    }

    @Override
    public void deleteTestPlan(UUID testPlanId, UUID projectId) {
        testPlanRepository.deleteTestPlan(testPlanId);
    }

    @Override
    public void editTestPlan(TestPlan testPlan) {
        List<UUID> newTestCasesId = new ArrayList<>();
        for (CaseDTO caseDTO : testPlan.getTestCases()) {
            newTestCasesId.add(caseDTO.caseId());
        }
        testPlanRepository.editTestPlanName(testPlan);
        testPlanRepository.deleteAllTestCasesInTestPlan(testPlan.getTestPlanId());
        addingTestCaseInTestPlan(newTestCasesId, testPlan.getTestPlanId());
    }

    @Override
    public GetTestPlanByIdResponse getTestPlanById(final UUID projectId, final UUID testPlanId) throws IdNotExist {
        try {
            return new GetTestPlanByIdResponse(testPlanRepository.getTestPlanById(projectId, testPlanId));
        } catch (IdNotExist e) {
            throw new IdNotExist("Test plan with id " + testPlanId + " not found");
        }
    }

    private void addingTestCaseInTestPlan(List<UUID> testcases, UUID addedTestPlan) {
        for (UUID testCaseId : testcases) {
            testPlanRepository.addTestCaseInTestPlan(testCaseId, addedTestPlan);
        }
    }
}
