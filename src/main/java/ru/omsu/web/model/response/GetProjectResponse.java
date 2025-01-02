package ru.omsu.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.omsu.core.model.Project;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetProjectResponse {

    private final Project project;
    private final int casesCount;
    private final int suitesCount;
}
