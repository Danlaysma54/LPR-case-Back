package core.service.tree;

import core.model.CaseAndSuite;

import java.util.List;
import java.util.UUID;

public interface ITreeService {
    List<CaseAndSuite> getFirstLevel(UUID projectId);
}
