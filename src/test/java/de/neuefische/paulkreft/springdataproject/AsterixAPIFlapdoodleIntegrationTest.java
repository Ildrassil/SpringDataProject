package de.neuefische.paulkreft.springdataproject;

import de.neuefische.paulkreft.springdataproject.utils.IdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AsterixAPIFlapdoodleIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public AsterixCharacterRepository asterixCharacterRepository;







    @Test
    @DirtiesContext
    public void getCharactersTest() throws Exception {

        asterixCharacterRepository.save(new AsterixCharacter("123","Obelix",10,"maison"));
        //WHEN

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters"))

                //then
                .andExpect(MockMvcResultMatchers.content().json("""
                                        [{
                                        
                                        "name": "Obelix",
                                         "age": 10
                                         }]
                                         """)).andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }


    @Test
    @DirtiesContext
    public void postCharactersTest() throws Exception{
        //GIVEN

        AsterixCharacterRequest asterixCharacterRequest = new AsterixCharacterRequest("Obelix",10,"maison");


        //WHEN
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/asterix/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                {
                                   "name": "Obelix",
                                   "age": "10",
                                   "profession": "maison"
                                }
                                """))
                //THEN
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                  {
                  "name": "Obelix",
                  "age": 10,
                  "profession": "maison"
                  }
                  """))
                .andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 200);


    }

   /* public void delteMappingTest() throws Exception{

        //GIVEN
        String Id = "1234";
        asterixCharacterRepository.save(new AsterixCharacter("1234",))
    }*/




    /*characterRepository.save(new Character("123", "Test"));

    //WHEN
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/characters"))

            //THEN
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().json("""
                        [{
                        "id": "123",
                        "name": "Test"
                        }]
                        """))
            .andReturn();


    assertEquals(mvcResult.getResponse().getStatus(), 200);
}*/
}



/*
    @GetMapping
    public List<AsterixCharacterResponse> getCharacters() {
        return asterixCharacterService.getAllCharacters();

    }

    @PostMapping
    public AsterixCharacter postCharacters(@RequestBody AsterixCharacterRequest request){
        return asterixCharacterService.createCharacter(request);
    }

    @GetMapping("/{id}")
    public AsterixCharacterResponse getCharacterById(@PathVariable String id) {
        return asterixCharacterService.getCharacterById(id);
    }

    @DeleteMapping("/{id}")
    public AsterixCharacter deleteCharacterById(@PathVariable String id) {
        return asterixCharacterService.deleteCharacterById(id);
    }

    @PutMapping("/{id}")
    public AsterixCharacter updateCharacterById(@PathVariable String id, @RequestBody AsterixCharacterRequest request) {
        return asterixCharacterService.updateCharacterById(id, request);
    }*/