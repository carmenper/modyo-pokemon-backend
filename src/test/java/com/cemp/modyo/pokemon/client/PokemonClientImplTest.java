package com.cemp.modyo.pokemon.client;

import com.cemp.modyo.pokemon.client.impl.PokeApiClientImpl;
import com.cemp.modyo.pokemon.client.impl.PokemonClientImpl;
import com.cemp.modyo.pokemon.dto.Poke;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.dto.PokeEvolution;
import com.cemp.modyo.pokemon.dto.PokeSpecies;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class PokemonClientImplTest {

    @InjectMocks
    private PokemonClientImpl pokemonClient;

    @Mock
    private PokeApiClientImpl pokeApiClient;

    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    void getPokeTest() throws IOException {
        Poke poke = mapper
                .readValue(new File("src/test/resources/json/pokes.json"),
                        Poke.class);
        Mono<Poke> pokeMono = Mono.just(poke);
        Mockito.when(pokeApiClient.retrievePoke(0, 0)).thenReturn(pokeMono);
        Poke result = pokemonClient.getPoke(0, 0);
        Assertions.assertNotNull(result);
    }

    @Test
    void getPokeDetailTest() throws IOException {
        PokeDetail pokeDetail = mapper
                .readValue(new File("src/test/resources/json/poke.json"),
                        PokeDetail.class);
        Mono<PokeDetail> pokeDetailMono = Mono.just(pokeDetail);
        Mockito.when(pokeApiClient
                .retrievePokeDetail("pikachu")).thenReturn(pokeDetailMono);
        PokeDetail result = pokemonClient.getPokeDetail("pikachu");
        Assertions.assertNotNull(result);
    }

    @Test
    void getPokeSpeciesTest() throws IOException {
        PokeSpecies pokeSpecies = mapper
                .readValue(new File("src/test/resources/json/poke_species.json"),
                        PokeSpecies.class);
        Mono<PokeSpecies> pokeSpeciesMono = Mono.just(pokeSpecies);
        Mockito.when(pokeApiClient
                .retrievePokeSpecies("pikachu")).thenReturn(pokeSpeciesMono);
        PokeSpecies result = pokemonClient.getPokeSpecies("pikachu");
        Assertions.assertNotNull(result);
    }

    @Test
    void getPokeEvolutionTest() throws IOException {
        PokeEvolution pokeEvolution = mapper
                .readValue(new File("src/test/resources/json/poke_evolution.json"),
                        PokeEvolution.class);
        Mono<PokeEvolution> pokeEvolutionMono = Mono.just(pokeEvolution);
        Mockito.when(pokeApiClient
                .retrievePokeEvolution("10")).thenReturn(pokeEvolutionMono);
        PokeEvolution result = pokemonClient.getPokeEvolution("10");
        Assertions.assertNotNull(result);
    }

}
