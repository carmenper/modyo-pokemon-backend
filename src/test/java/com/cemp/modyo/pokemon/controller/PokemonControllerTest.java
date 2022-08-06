package com.cemp.modyo.pokemon.controller;

import com.cemp.modyo.pokemon.domain.PokemonDetail;
import com.cemp.modyo.pokemon.domain.PokemonWrapper;
import com.cemp.modyo.pokemon.service.PokemonService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PokemonController.class)
class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonController pokemonController;

    @MockBean
    private PokemonService pokemonService;

    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @InjectMocks
    private PokemonController pokemonController2;

    @Mock
    private PokemonService pokemonService2;

    @Test
    void getPokemonsTest() throws Exception {

        PokemonWrapper pokemonWrapper = new PokemonWrapper();
        Mockito.when(pokemonService.getPokemons(0, 0)).thenReturn(pokemonWrapper);
        this.mockMvc.perform(get("/pokemon")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void getPokemons2Test() {
        PokemonWrapper pokemonWrapper = new PokemonWrapper();
        Mockito.when(pokemonService2.getPokemons(0, 0)).thenReturn(pokemonWrapper);
        PokemonWrapper pw = (PokemonWrapper) pokemonController2
                .getPokemons("0", "0").getBody();
        Assertions.assertNotNull(pw);
    }

    @Test
    void getPokemonTest() throws Exception {
        PokemonDetail pokemonDetail = new PokemonDetail();
        String id = "pikachu";
        Mockito.when(pokemonService.getPokemon(id)).thenReturn(pokemonDetail);
        this.mockMvc.perform(get("/pokemon/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getPokemon2Test() {
        PokemonDetail pokemonDetail = new PokemonDetail();
        String id = "pikachu";
        Mockito.when(pokemonService2.getPokemon(id)).thenReturn(pokemonDetail);
        PokemonDetail pd = (PokemonDetail) pokemonController2.getPokemon(id).getBody();
        Assertions.assertNotNull(pd);
    }


}
