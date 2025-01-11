package ru.omsu.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class AddProjectResponse {
    private UUID projectUUID;
}
