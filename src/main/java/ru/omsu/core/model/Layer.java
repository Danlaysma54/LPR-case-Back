package ru.omsu.core.model;

import java.util.UUID;

/**
 * layers of test case
 */
public class Layer {
    /**
     * name of the layer
     */
    private final String layerName;
    /**
     * id of layer
     */
    private final UUID layerId;

    /**
     *
     * @param layerName name of the layer
     * @param layerId id of the layer
     */
    public Layer(final String layerName, final UUID layerId) {
        this.layerName = layerName;
        this.layerId = layerId;
    }

    public String getLayerName() {
        return layerName;
    }

    public UUID getLayerId() {
        return layerId;
    }

}
