package core.model;

import java.util.UUID;

public record CaseAndSuite(UUID id, UUID root_id, String name, boolean isSuite) {
}
