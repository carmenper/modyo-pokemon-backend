package com.cemp.modyo.pokemon.util;

import com.cemp.modyo.pokemon.client.PokemonClient;
import com.cemp.modyo.pokemon.domain.Pokemon;
import com.cemp.modyo.pokemon.dto.Poke;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.dto.PokeEvolution;
import com.cemp.modyo.pokemon.dto.PokeSpecies;
import com.cemp.modyo.pokemon.pojo.PokemonSpecies;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class PokemonBuilderTest {

    @InjectMocks
    private PokemonBuilder pokemonBuilder;

    @Mock
    private PokemonClient pokemonClient;

    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    void getPokemonList() throws IOException {

        Poke poke = mapper
                .readValue(new File("src/test/resources/json/pokes.json"),
                        Poke.class);
        PokeDetail pokeDetail = mapper
                .readValue(new File("src/test/resources/json/poke.json"),
                        PokeDetail.class);
        Mockito.when(pokemonClient.getPokeDetail(anyString())).thenReturn(pokeDetail);
        List<Pokemon> pokemonList = pokemonBuilder.getPokemonList(poke);
        Assertions.assertNotNull(pokemonList);
        Assert.noNullElements(pokemonList, "");

    }

    @Test
    void getPokemonTest() throws IOException {

        PokeDetail pokeDetail = mapper
                .readValue(new File("src/test/resources/json/poke.json"),
                        PokeDetail.class);
        Pokemon pokemon = pokemonBuilder.getPokemon(pokeDetail);
        Assertions.assertNotNull(pokemon);

    }

    @Test
    void getPokemonSpecies() throws IOException {

        PokeDetail pokeDetail = mapper
                .readValue(new File("src/test/resources/json/poke.json"),
                        PokeDetail.class);
        PokeSpecies pokeSpecies = mapper
                .readValue(new File("src/test/resources/json/poke_species.json"),
                        PokeSpecies.class);
        Mockito.when(pokemonClient.getPokeDetail("pikachu")).thenReturn(pokeDetail);
        Mockito.when(pokemonClient.getPokeSpecies("pikachu")).thenReturn(pokeSpecies);
        PokemonSpecies pokemonSpecies = pokemonBuilder.getPokemonSpecies("pikachu");
        Assertions.assertNotNull(pokemonSpecies);

    }

    @Test
    void getPokemonDescriptionsTest() throws IOException {

        PokeSpecies pokeSpecies = mapper
                .readValue(new File("src/test/resources/json/poke_species.json"),
                        PokeSpecies.class);
        Assertions.assertNotNull(pokemonBuilder.getPokemonDescriptions(pokeSpecies.getDescriptions()));

    }

    @Test
    void getPokemonGenusTest() throws IOException {
        PokeSpecies pokeSpecies = mapper
                .readValue(new File("src/test/resources/json/poke_species.json"),
                        PokeSpecies.class);
        Assertions.assertNotNull(pokemonBuilder.getPokemonGenus(pokeSpecies.getGenera()));
    }

    @Test
    void getPokemonEvolutionsTest() throws IOException {
        PokeEvolution pokeEvolution = mapper
                .readValue(new File("src/test/resources/json/poke_evolution.json"),
                        PokeEvolution.class);
        Mockito.when(pokemonClient.getPokeEvolution("10"))
                .thenReturn(pokeEvolution);

        Assertions.assertNotNull(pokemonBuilder.getPokemonEvolutions("10"));
    }


}
