package core.model;

import java.util.UUID;

public record CaseAndSuiteResponse(UUID id, UUID root_id, String name, boolean isSuite) {
}
