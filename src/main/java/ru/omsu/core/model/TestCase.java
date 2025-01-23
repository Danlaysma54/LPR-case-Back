package ru.omsu.core.model;



import java.util.List;
import java.util.UUID;

/**
 *
 * @param testCaseId id of test case
 * @param testCaseName name of the test case
 * @param layer layer of test case
 * @param isAutomated is it automated
 * @param suiteId id of suite
 */
public record TestCase(UUID testCaseId, String testCaseName, String layer, String isAutomated, UUID suiteId, List<Step> stepList) {

}
