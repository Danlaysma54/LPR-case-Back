package core.service.tree;

import web.model.OneLevelResponse;
import java.util.UUID;

public interface ITreeService {
    OneLevelResponse getOneLevel(UUID suiteId);

}
