package ru.omsu.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 *  class request to add project
 */
@Data
@AllArgsConstructor
public class AddProjectResponse {
    private UUID projectUUID;

    /**
     *
     * @param uuid id of project
     */
    public AddProjectResponse(final UUID uuid) {
    }
}
