package ru.omsu.core.service.tree;

import ru.omsu.web.model.OneLevelResponse;
import java.util.UUID;

public interface ITreeService {
    OneLevelResponse getOneLevel(UUID suiteId);

}
