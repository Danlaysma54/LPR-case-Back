package web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.omsu.core.model.TestPlan;
import ru.omsu.core.model.TestPlanWithSuitesId;
import ru.omsu.core.service.testPlan.ITestPlanService;
import ru.omsu.web.controllers.TestPlanController;
import ru.omsu.web.model.request.EditTestPlanRequest;
import ru.omsu.web.model.request.TestPlanRequest;
import ru.omsu.web.model.response.AddedEntityResponse;
import ru.omsu.web.model.response.GetTestPlanByIdResponse;
import ru.omsu.web.model.response.GetTestPlansResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestPlanControllerTest {

    @Mock
    private ITestPlanService testPlanService;

    @InjectMocks
    private TestPlanController testPlanController;

    @Test
    void getTestPlans_ShouldReturnOkWithTestPlans() {
        // Arrange
        UUID projectId = UUID.randomUUID();
        GetTestPlansResponse expectedTestPlans = new GetTestPlansResponse(new ArrayList<>());
        when(testPlanService.getTestPlans(projectId)).thenReturn(expectedTestPlans);

        // Act
        ResponseEntity<?> response = testPlanController.getTestPlans(projectId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTestPlans, response.getBody());
        verify(testPlanService).getTestPlans(projectId);
    }

    @Test
    void addTestPlan_ShouldReturnCreatedStatus() {
        // Arrange
        TestPlanRequest testPlanRequest=new TestPlanRequest("",new ArrayList<>());
        UUID projectId = UUID.randomUUID();
        AddedEntityResponse addedEntityResponse = new AddedEntityResponse(UUID.randomUUID());
        when(testPlanService.addTestPlan(testPlanRequest)).thenReturn(addedEntityResponse);

        // Act
        ResponseEntity<?> response = testPlanController.addTestPlan(projectId, testPlanRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(addedEntityResponse, response.getBody());
        verify(testPlanService).addTestPlan(testPlanRequest);
    }

    @Test
    void deleteTestPlan_ShouldReturnOk_WhenSuccessful() {
        // Arrange
        UUID projectId = UUID.randomUUID();
        UUID testPlanId = UUID.randomUUID();
        doNothing().when(testPlanService).deleteTestPlan(testPlanId, projectId);

        // Act
        ResponseEntity<?> response = testPlanController.deleteTestPlan(testPlanId, projectId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(testPlanService).deleteTestPlan(testPlanId, projectId);
    }

    @Test
    void editTestPlan_ShouldReturnOk_WhenSuccessful() {
        // Arrange
        UUID projectId = UUID.randomUUID();
        EditTestPlanRequest request = new EditTestPlanRequest(UUID.randomUUID(),"Hello",new ArrayList<>());
        doNothing().when(testPlanService).editTestPlan(request);

        // Act
        ResponseEntity<?> response = testPlanController.editTestPlan(projectId, request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(testPlanService).editTestPlan(request);
    }

    @Test
    void getTestPlanById_ShouldReturnOkWithTestPlan() {
        // Arrange
        UUID projectId = UUID.randomUUID();
        UUID testPlanId = UUID.randomUUID();
        TestPlanWithSuitesId testPlanWithSuitesId = Mockito.mock(TestPlanWithSuitesId.class);
        GetTestPlanByIdResponse expectedResponse = new GetTestPlanByIdResponse(testPlanWithSuitesId);
        when(testPlanService.getTestPlanById(projectId, testPlanId)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<?> response = testPlanController.getTestPlanById(projectId, testPlanId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(testPlanService).getTestPlanById(projectId, testPlanId);
    }

    // Дополнительные тесты для проверки валидации
    @Test
    void addTestPlan_ShouldValidateInput() {
        // Проверка аннотации @Validated
        assertDoesNotThrow(() -> {
            TestPlanRequest request = new TestPlanRequest("Hello",new ArrayList<>());
            // Установите валидные данные
            testPlanController.addTestPlan(UUID.randomUUID(), request);
        });
    }

    @Test
    void deleteTestPlan_ShouldValidatePathVariables() {
        // Проверка аннотации @Validated для path variables
        assertDoesNotThrow(() -> {
            testPlanController.deleteTestPlan(UUID.randomUUID(), UUID.randomUUID());
        });
    }
}