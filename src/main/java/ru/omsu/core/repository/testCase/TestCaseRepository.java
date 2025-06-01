package ru.omsu.core.repository.testCase;

import ru.omsu.core.model.Automation;
import ru.omsu.core.model.Layer;
import ru.omsu.core.model.Step;
import ru.omsu.core.model.TestCase;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.omsu.web.model.request.EditTestCaseRequest;
import ru.omsu.web.model.request.StepsRequest;
import ru.omsu.web.model.request.TestCaseRequest;
import ru.omsu.web.model.response.TestCaseTypes;

import java.util.List;
import java.util.UUID;

/**
 * class implementation of test case Repository by postgres
 */
@Repository
public class TestCaseRepository implements ITestCaseRepository {
    private final JdbcOperations jdbcOperations;

    /**
     * @param jdbcOperations jdbc
     */
    public TestCaseRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    /**
     * @param testCaseRequest object for request to add test case
     * @return UUID of added test case
     */
    @Override
    public UUID addTestCase(final TestCaseRequest testCaseRequest) {
        return jdbcOperations.queryForObject("INSERT INTO test_case(test_case_name,suite_id,automation_status_id,layer_id) VALUES (?,?,?,?) RETURNING test_case_id",
                (resultSet, i) -> UUID.fromString(resultSet.getString("test_case_id")),
                testCaseRequest.testCaseName(), testCaseRequest.suiteId(),
                testCaseRequest.isAutomatedId(), testCaseRequest.layerId());
    }

    /**
     * @param testCaseId id of test case
     * @return test case entity
     */
    @Override
    public TestCase getTestCase(final UUID testCaseId) {
        return jdbcOperations.queryForObject("select test_case_id,test_case_name,suite_id,layer_name,automation_name from test_case" +
                        " inner join layer on layer.layer_id=test_case.layer_id " +
                        "inner join automation_status on automation_status.automation_status_id=test_case.automation_status_id" +
                        " where test_case_id=?",
                (resultSet, i) -> new TestCase(
                        UUID.fromString(resultSet.getString("test_case_id")),
                        resultSet.getString("test_case_name"),
                        resultSet.getString("layer_name"),
                        resultSet.getString("automation_name"),
                        UUID.fromString(resultSet.getString("suite_id"))),
                testCaseId);
    }

    /**
     * @param testCaseId id of test case
     */
    @Override
    public void deleteTestCase(final UUID testCaseId) {
        if (jdbcOperations.update("DELETE FROM test_case where test_case_id = CAST(? AS UUID)", testCaseId.toString()) < 1) {
            throw new IllegalArgumentException("Test Case with this ID doesn't exist");
        }
    }

    /**
     * @param testCase entity of test case
     */
    @Override
    public void editTestCase(final EditTestCaseRequest testCase) {
        if (jdbcOperations.update("UPDATE test_case SET test_case_name=?,suite_id=?,automation_status_id=?,layer_id=? where test_case_id=?",
                testCase.getTestCaseName(), testCase.getSuiteId(), testCase.getIsAutomated(), testCase.getLayer(), testCase.getTestCaseId()) < 1) {
            throw new IllegalArgumentException("Test Case with that Id doesn't exist");
        }
    }

    @Override
    public void editTestStep(Step step) {
        if (jdbcOperations.update("UPDATE test_step set step_description=?,step_data=?,step_result=?,step_number=? where test_step_id=?",
                step.stepDescription(), step.stepData(), step.stepResult(), step.stepNumber(), step.testStepId()) < 1) {
            throw new IllegalArgumentException("Test Step with that Id doesn't exist");
        }
    }

    /**
     * @param testCaseId id of test case
     * @return list of cases steps
     */
    @Override
    public List<Step> getTestCaseSteps(final UUID testCaseId) {
        return jdbcOperations.query("SELECT test_step_id,step_description,step_data,step_result,step_number from test_step where test_case_id=?",
                (resultSet, i) -> new Step(UUID.fromString(resultSet.getString("test_step_id")), resultSet.getString("step_description"), resultSet.getString("step_data"),
                        resultSet.getString("step_result"), resultSet.getInt("step_number"))
                , testCaseId);
    }

    @Override
    public List<Layer> getTestCaseLayers() {
        return jdbcOperations.query("SELECT layer_id,layer_name from layer",
                (resultSet, i) ->
                        new Layer(resultSet.getString("layer_name"), UUID.fromString(resultSet.getString("layer_id"))));
    }

    @Override
    public List<Automation> getTestCaseAutomation() {
        return jdbcOperations.query("SELECT automation_status_id,automation_name from automation_status", (resultSet, i) ->
                new Automation(resultSet.getString("automation_name"), UUID.fromString(resultSet.getString("automation_status_id"))));
    }

    @Override
    public void addTestSteps(final StepsRequest step, final UUID testCaseId) {
        jdbcOperations.update("INSERT INTO test_step(step_description,test_case_id,step_data,step_result,step_number) VALUES(?,?,?,?,?)",
                step.getStepDescription(),
                testCaseId,
                step.getStepData(),
                step.getStepResult(),
                step.getStepNumber());
    }
}
