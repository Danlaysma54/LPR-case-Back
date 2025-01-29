package ru.omsu.core.model;


import java.util.List;
import java.util.UUID;

/**
 *
 * @param suiteName name of suite
 * @param suiteId id of suite
 */
public record AllSuitesInProject(String suiteName, UUID suiteId, List<AllSuitesInProject> children) {
}
