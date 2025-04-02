package ru.omsu.web.model.response;

import ru.omsu.core.model.Automation;
import ru.omsu.core.model.Layer;

import java.util.List;

/**
 * class with all types of test cases
 */
public class TestCaseTypes {
    private List<Layer> layers;
    private List<Automation> automations;

    /**
     *
     * @param layers of test case
     * @param automations of test case
     */
    public TestCaseTypes(final List<Layer> layers, final List<Automation> automations) {
        this.layers = layers;
        this.automations = automations;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public List<Automation> getAutomations() {
        return automations;
    }
}
