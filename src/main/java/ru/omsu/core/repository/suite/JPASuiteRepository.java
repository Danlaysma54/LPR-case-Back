package ru.omsu.core.repository.suite;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ru.omsu.core.model.Suite;
import ru.omsu.web.model.request.AddSuiteRequest;

import java.util.List;
import java.util.UUID;

public class JPASuiteRepository implements  ISuiteRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Suite getSuite(UUID suiteId) {
        return entityManager.find(Suite.class,suiteId);
    }

    @Override
    public UUID addSuite(Suite addSuiteRequest) {
        return entityManager.createNativeQuery("").unwrap(UUID.class);
    }

    @Override
    public void editSuite(Suite suite) {

    }

    @Override
    public void deleteSuite(UUID suiteId) {
        Suite suite = entityManager.find(Suite.class,suiteId);
        entityManager.remove(suite);

    }

    @Override
    public List<UUID> getAllSuitesInProject(UUID projectId) {
        return List.of();
    }
}
