package ru.omsu.core.model;

import java.util.UUID;

/**
 * @param stepDescription name of the step
 * @param stepData        data for step
 * @param stepResult      expected result of step
 * @param stepNumber      serial number of step
 */
public record Step(String stepDescription, String stepData, String stepResult, int stepNumber, UUID step_case) {
}
