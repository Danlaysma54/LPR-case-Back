package ru.omsu.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.omsu.core.model.CaseDTO;
import ru.omsu.core.model.Suite;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
public class OneLevelResponse {
    List<CaseDTO> cases;
    List<Suite> suites;
    String suiteName;
    UUID suiteId;
}
