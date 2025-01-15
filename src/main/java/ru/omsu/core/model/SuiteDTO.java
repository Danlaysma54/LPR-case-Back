package ru.omsu.core.model;


import java.util.UUID;

/**
 *
 * @param suiteName name of suite
 * @param suiteRootId id of root suite
 * @param suiteId id of suite
 */
public record SuiteDTO(String suiteName, UUID suiteRootId, UUID suiteId) {
}
