package core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.omsu.core.model.Suite;
import ru.omsu.core.repository.suite.ISuiteRepository;
import ru.omsu.core.service.suite.SuiteService;
import ru.omsu.web.model.exception.IdNotExist;
import ru.omsu.web.model.request.AddSuiteRequest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SuiteServiceTest {

    private ISuiteRepository suiteRepository;
    private SuiteService suiteService;

    @BeforeEach
    void setUp() {
        suiteRepository = mock(ISuiteRepository.class);
        suiteService = new SuiteService(suiteRepository);
    }

    @Test
    void testAddSuite_success() throws IdNotExist {
        UUID rootId = UUID.randomUUID();
        UUID newSuiteId = UUID.randomUUID();
        AddSuiteRequest request = mock(AddSuiteRequest.class);
        Suite mockSuite = mock(Suite.class);

        when(request.getSuiteRootId()).thenReturn(rootId);
        when(suiteRepository.getSuite(rootId)).thenReturn(mockSuite);
        when(suiteRepository.addSuite(request)).thenReturn(newSuiteId);

        UUID result = suiteService.addSuite(request);

        assertEquals(newSuiteId, result);
        verify(suiteRepository).getSuite(rootId);
        verify(suiteRepository).addSuite(request);
    }

    @Test
    void testAddSuite_throwsIdNotExist() throws IdNotExist {
        UUID rootId = UUID.randomUUID();
        AddSuiteRequest request = mock(AddSuiteRequest.class);

        when(request.getSuiteRootId()).thenReturn(rootId);
        doThrow(new IdNotExist("Root ID not found")).when(suiteRepository).getSuite(rootId);

        assertThrows(IdNotExist.class, () -> suiteService.addSuite(request));
        verify(suiteRepository).getSuite(rootId);
        verify(suiteRepository, never()).addSuite(any());
    }

    @Test
    void testEditSuite_success() throws IdNotExist {
        UUID rootId = UUID.randomUUID();
        UUID suiteId = UUID.randomUUID();
        Suite suite = mock(Suite.class);
        Suite suiteFromDb = mock(Suite.class);

        when(suite.getSuiteRootId()).thenReturn(rootId);
        when(suite.getSuiteId()).thenReturn(suiteId);
        doNothing().when(suiteRepository).editSuite(suite);
        when(suiteRepository.getSuite(suiteId)).thenReturn(suiteFromDb);

        Suite result = suiteService.editSuite(suite);

        assertEquals(suiteFromDb, result);
        verify(suiteRepository).getSuite(rootId);
        verify(suiteRepository).editSuite(suite);
        verify(suiteRepository).getSuite(suiteId);
    }

    @Test
    void testDeleteSuite_success() throws IdNotExist {
        UUID suiteId = UUID.randomUUID();

        doNothing().when(suiteRepository).deleteSuite(suiteId);

        suiteService.deleteSuite(suiteId);

        verify(suiteRepository).deleteSuite(suiteId);
    }

    @Test
    void testDeleteSuite_throwsIdNotExist() throws IdNotExist {
        UUID suiteId = UUID.randomUUID();

        doThrow(new IdNotExist("Suite not found")).when(suiteRepository).deleteSuite(suiteId);

        assertThrows(IdNotExist.class, () -> suiteService.deleteSuite(suiteId));
        verify(suiteRepository).deleteSuite(suiteId);
    }
}
