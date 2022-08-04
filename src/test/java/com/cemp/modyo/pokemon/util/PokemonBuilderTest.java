package com.cemp.modyo.pokemon.util;

import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.dto.PokeEvolution;
import com.cemp.modyo.pokemon.dto.PokeSpecies;
import com.cemp.modyo.pokemon.exception.ApplicationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class PokemonBuilderTest {

    @InjectMocks
    PokemonBuilder pokemonBuilder;

    @Mock
    PokemonUtility pokemonUtility;

    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    void getPokeMarkerTest() {
        Assertions.assertNull(pokemonBuilder.getPokeMarker(null));
        Assertions.assertNull(pokemonBuilder.getPokeMarker(""));
        Assertions
                .assertNotNull(pokemonBuilder
                .getPokeMarker("https://pokeapi.co/api/v2/pokemon?offset=200&limit=100"));
    }

    @Test
    void getPokemonTest() throws IOException {

        Assertions.assertThrows(ApplicationException.class, () -> {
            pokemonBuilder.getPokemon(null);
        });

        PokeDetail pokeDetail = mapper
                .readValue(new File("src/test/resources/json/poke.json"),
                        PokeDetail.class);

        Mockito.when(pokemonUtility.getTypes(pokeDetail)).thenReturn(new ArrayList<>());
        Mockito.when(pokemonUtility.getAbilities(pokeDetail)).thenReturn(new ArrayList<>());

        Assertions.assertNotNull(pokemonBuilder.getPokemon(pokeDetail));

    }

    @Test
    void getPokemonDescriptionsTest() throws IOException {
        PokeSpecies pokeSpecies = mapper
                .readValue(new File("src/test/resources/json/poke_species.json"),
                        PokeSpecies.class);
        Assertions.assertNotNull(pokemonBuilder.getPokemonDescriptions(pokeSpecies));

    }

    @Test
    void getGenusTest() throws IOException {
        PokeSpecies pokeSpecies = mapper
                .readValue(new File("src/test/resources/json/poke_species.json"),
                        PokeSpecies.class);
        Assertions.assertNotNull(pokemonBuilder.getGenus(pokeSpecies));
    }

    @Test
    void getPokemonEvolutionsTest() throws IOException {
        PokeEvolution pokeEvolution = mapper
                .readValue(new File("src/test/resources/json/poke_evolution.json"),
                        PokeEvolution.class);
        Mockito.when(pokemonUtility.getEvolutionChain(pokeEvolution.getChain()))
                .thenReturn(new ArrayList<>());
        Assertions.assertNotNull(pokemonBuilder.getPokemonEvolutions(pokeEvolution.getChain()));
    }


}
