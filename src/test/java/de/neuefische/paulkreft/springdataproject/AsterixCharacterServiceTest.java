package de.neuefische.paulkreft.springdataproject;

import de.neuefische.paulkreft.springdataproject.utils.IdService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AsterixCharacterServiceTest {

    @Test
    void getAllCharacters() {
        AsterixCharacterRepository asterixCharacterRepository = Mockito.mock(AsterixCharacterRepository.class);
        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(asterixCharacterRepository, new IdService());
        AsterixCharacter asterixCharacter = new AsterixCharacter("1","Asterix",35,"Krieger");
        AsterixCharacter asterixCharacter2 = new AsterixCharacter("2","Obelix",35,"Steinmetz");
        Mockito.when(asterixCharacterRepository.findAll()).thenReturn(List.of(asterixCharacter, asterixCharacter2));

        AsterixCharacterResponse asterixCharacterResponse = new AsterixCharacterResponse("Asterix",35,"Krieger");
        AsterixCharacterResponse asterixCharacterResponse2 = new AsterixCharacterResponse("Obelix",35,"Steinmetz");

        assertEquals(List.of(asterixCharacterResponse,asterixCharacterResponse2),asterixCharacterService.getAllCharacters());

    }



    @Test
    void getCharacterById() {
        AsterixCharacterRepository asterixCharacterRepository = Mockito.mock(AsterixCharacterRepository.class);
        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(asterixCharacterRepository,new IdService());
        AsterixCharacter asterixCharacter = new AsterixCharacter("1","Asterix",35,"Krieger");
        String id = "1";

        Mockito.when(asterixCharacterRepository.findById(id)).thenReturn(Optional.of(asterixCharacter));

        AsterixCharacterResponse asterixCharacterResponse = new AsterixCharacterResponse("Asterix",35,"Krieger");

        assertEquals(asterixCharacterResponse,asterixCharacterService.getCharacterById(id));
    }

    @Test
    void deleteCharacterById() {
        AsterixCharacterRepository asterixCharacterRepository = Mockito.mock(AsterixCharacterRepository.class);
        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(asterixCharacterRepository,new IdService());
        AsterixCharacter asterixCharacter = new AsterixCharacter("1","Asterix",35,"Krieger");
        String id = "1";

        Mockito.doNothing().when(asterixCharacterRepository).deleteById(id);
        Mockito.when(asterixCharacterRepository.findById(id)).thenReturn(Optional.of(asterixCharacter));

        assertEquals(asterixCharacter, asterixCharacterService.deleteCharacterById(id));

    }

    @Test
    void updateCharacterById() {
        AsterixCharacterRepository asterixCharacterRepository = Mockito.mock(AsterixCharacterRepository.class);
        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(asterixCharacterRepository,new IdService());
        AsterixCharacter asterixCharacter = new AsterixCharacter("1","Asterix",35,"Krieger");
        String id = "1";


        AsterixCharacterRequest asterixCharacterResponse = new AsterixCharacterRequest("Asterix",35,"Krieger");
        Mockito.when(asterixCharacterRepository.findById(id)).thenReturn(Optional.of(asterixCharacter));
        Mockito.doNothing().when(asterixCharacterRepository).deleteById(id);
        Mockito.when(asterixCharacterRepository.save(asterixCharacter)).thenReturn(asterixCharacter);

        assertEquals(asterixCharacter,asterixCharacterService.updateCharacterById(id,asterixCharacterResponse));

    }
}