package de.neuefische.paulkreft.springdataproject.utils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class IdServiceTest {


    @Test
    void randomId() {
        UUID mockUUID = UUID.randomUUID();
        MockedStatic<UUID> mock = Mockito.mockStatic(UUID.class);
        IdService idService = new IdService();
        mock.when(UUID::randomUUID).thenReturn(mockUUID);

        UUID generatedUUID = UUID.randomUUID();

        // Assertions
        assertEquals(generatedUUID.toString(), idService.randomId());

    }
}
