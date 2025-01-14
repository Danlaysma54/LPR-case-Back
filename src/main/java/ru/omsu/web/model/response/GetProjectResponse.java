package ru.omsu.web.model.response;


import ru.omsu.core.model.Project;

public record GetProjectResponse (Project project, int casesCount, int suitesCount){
}
