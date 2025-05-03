package ru.omsu.core.repository.testPlan;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import ru.omsu.core.model.*;
import ru.omsu.web.model.exception.IdNotExist;
import ru.omsu.web.model.request.TestPlanRequest;

import java.util.List;
import java.util.UUID;

/**
 * Class for working with db for test plan repository
 */
public class TestPlanRepository implements ITestPlanRepository {

    private final JdbcOperations jdbcOperations;

    public TestPlanRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public UUID addTestPlan(TestPlanRequest testPlanRequest) {
        return jdbcOperations.queryForObject("INSERT INTO test_plan(test_plan_name) values(?) RETURNING test_plan_id",
                (resultSet, i) -> (UUID.fromString(resultSet.getString("test_plan_id"))), testPlanRequest.getTestPlanName());
    }

    @Override
    public void addTestCaseInTestPlan(UUID testCaseId, UUID testPlanId) {
        jdbcOperations.update("INSERT INTO test_cases_in_test_plan(test_plan_id,test_case_id) values(?,?)", testPlanId, testCaseId);
    }

    @Override
    public List<TestPlanDTO> getTestPlans(UUID projectId) {
        return jdbcOperations.query("SELECT test_plan_id,test_plan_name from test_plan",
                (resultSet, i) -> (new TestPlanDTO(UUID.fromString(resultSet.getString("test_plan_id")),
                        resultSet.getString("test_plan_name"))));
    }

    @Override
    public List<CaseDTO> getCaseInTestPlans(UUID testPlanId) {
        return jdbcOperations.query("select test_case.test_case_id,test_case_name from" +
                        " test_case inner join test_cases_in_test_plan on test_case.test_case_id=test_cases_in_test_plan.test_case_id" +
                        " where test_cases_in_test_plan.test_plan_id = ?",
                (resultSet, i) -> (new CaseDTO(resultSet.getString("test_case_name"),
                        UUID.fromString(resultSet.getString("test_case_id")))), testPlanId);
    }

    @Override
    public List<CaseForPlanDTO> getCaseWithSuitesInTestPlans(UUID testPlanId) {
        return jdbcOperations.query("select test_case.test_case_id,test_case_name, suite_id from" +
                        " test_case inner join test_cases_in_test_plan on test_case.test_case_id=test_cases_in_test_plan.test_case_id" +
                        " where test_cases_in_test_plan.test_plan_id = ?",
                (resultSet, i) -> (
                        new CaseForPlanDTO(
                                resultSet.getString("test_case_name"),
                                UUID.fromString(resultSet.getString("test_case_id")),
                                UUID.fromString(resultSet.getString("suite_id"))
                        )
                ), testPlanId);
    }


    @Override
    public void deleteTestPlan(UUID testPlanId) {
        jdbcOperations.update("DELETE FROM test_plan where test_plan_id=?", testPlanId);
    }

    @Override
    public void editTestCasesInTestPlan(UUID testCaseId) {
        jdbcOperations.update("");
    }

    @Override
    public void editTestPlanName(TestPlan testPlan) {
        jdbcOperations.update("UPDATE test_plan set test_plan_name=? where test_plan_id=?",
                testPlan.getTestPlanName(),
                testPlan.getTestPlanId());
    }

    @Override
    public void deleteAllTestCasesInTestPlan(UUID testPlanId) {
        jdbcOperations.update("delete from test_cases_in_test_plan where test_plan=?");
    }

    @Override
    public TestPlanWithSuitesId getTestPlanById(UUID projectId, UUID testPlanId) {
        try {
            List<CaseForPlanDTO> testCasesInPlan = getCaseWithSuitesInTestPlans(testPlanId);
            return jdbcOperations.queryForObject("SELECT test_plan_id,test_plan_name from test_plan where test_plan_id=?",
                    (resultSet, i) -> (
                            new TestPlanWithSuitesId(UUID.fromString(resultSet.getString("test_plan_id")),
                                    resultSet.getString("test_plan_name"), testCasesInPlan)
                    ), testPlanId);
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotExist("Test plan with that id doesn't exist");
        }
    }

}
