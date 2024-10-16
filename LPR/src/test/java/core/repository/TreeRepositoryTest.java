package core.repository;

import core.repository.tree.TreeRepository;
import org.springframework.jdbc.core.JdbcOperations;

import static org.mockito.Mockito.mock;

public class TreeRepositoryTest {
    private TreeRepository treeRepository;
    private JdbcOperations jdbcOperations;

    public void setUp() {
        jdbcOperations = mock(JdbcOperations.class);
        treeRepository = new TreeRepository(jdbcOperations);
    }
}
