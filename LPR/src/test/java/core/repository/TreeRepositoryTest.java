package core.repository;

import core.model.CaseAndSuiteResponse;
import core.model.CaseDTO;
import core.model.Suite;
import core.repository.tree.TreeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TreeRepositoryTest {
    private TreeRepository treeRepository;
    private JdbcOperations jdbcOperations;

    @BeforeEach
    public void setUp() {
        jdbcOperations = mock(JdbcOperations.class);
        treeRepository = new TreeRepository(jdbcOperations);
    }

    @Test
    public void getFirstLevelTestCases() {
        UUID projectId = mock(UUID.class);
        List<CaseAndSuiteResponse> mockList = mock(List.class);
        when(jdbcOperations.query(anyString(),any(RowMapper.class),anyString())).thenReturn(mockList);
        List<CaseDTO> response = treeRepository.getOneLevelCases(projectId);
        Assertions.assertEquals(mockList, response);
    }
    @Test
    public void getFirstLevelTestSuites(){
        UUID projectId = mock(UUID.class);
        List<CaseDTO> mockList = mock(List.class);
        when(jdbcOperations.query(anyString(),any(RowMapper.class),anyString())).thenReturn(mockList);
        List<Suite> response = treeRepository.getOneLevelSuites(projectId);
        Assertions.assertEquals(mockList, response);
    }
}
