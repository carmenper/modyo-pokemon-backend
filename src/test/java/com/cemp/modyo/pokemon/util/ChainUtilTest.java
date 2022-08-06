package com.cemp.modyo.pokemon.util;

import com.cemp.modyo.pokemon.dto.PokeEvolution;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

class ChainUtilTest {

    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    void processChain() throws IOException {
        PokeEvolution pokeEvolution = mapper
                .readValue(new File("src/test/resources/json/poke_evolution.json"), PokeEvolution.class);
        List<String> evolutions = PokemonUtility.getEvolutionChain(pokeEvolution.getChain());
        Assertions.assertEquals(3, evolutions.size());
    }
}
