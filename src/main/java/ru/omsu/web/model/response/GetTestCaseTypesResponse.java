package ru.omsu.web.model.response;

import ru.omsu.core.model.Automation;
import ru.omsu.core.model.Layer;

import java.util.List;

public record GetTestCaseTypesResponse(List<Layer> layersTypes, List<Automation> automationsTypes) {

}
