package com.cemp.modyo.pokemon.service;

import com.cemp.modyo.pokemon.client.impl.PokemonClientImpl;
import com.cemp.modyo.pokemon.domain.Pokemon;
import com.cemp.modyo.pokemon.domain.PokemonDetail;
import com.cemp.modyo.pokemon.domain.PokemonWrapper;
import com.cemp.modyo.pokemon.dto.Poke;
import com.cemp.modyo.pokemon.dto.PokeDescription;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.dto.PokeGenus;
import com.cemp.modyo.pokemon.pojo.PokemonSpecies;
import com.cemp.modyo.pokemon.service.impl.PokemonServiceImpl;
import com.cemp.modyo.pokemon.util.PokemonBuilder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PokemonServiceImplTest {

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Mock
    private PokemonClientImpl pokemonClient;

    @Mock
    private PokemonBuilder pokemonBuilder;

    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    void getPokemonsTest() throws IOException {

        ReflectionTestUtils.setField(pokemonService, "pokemonBuilder", pokemonBuilder);
        Poke poke = mapper
                .readValue(new File("src/test/resources/json/pokes.json"),
                        Poke.class);
        List<Pokemon> pokemons = new ArrayList<>();
        Mockito.when(pokemonClient.getPoke(0 ,0)).thenReturn(poke);
        Mockito.when(pokemonBuilder.getPokemonList(poke)).thenReturn(pokemons);
        PokemonWrapper pokemonWrapper = pokemonService.getPokemons(0 , 0);
        Assertions.assertNotNull(pokemonWrapper);

    }

    @Test
    void getPokemonTest() throws IOException {

        ReflectionTestUtils.setField(pokemonService, "pokemonBuilder", pokemonBuilder);
        String id = "pikachu";
        PokeDetail pokeDetail = mapper
                .readValue(new File("src/test/resources/json/poke.json"), PokeDetail.class);
        PokemonSpecies pokemonSpecies = mapper
                .readValue(new File("src/test/resources/json/pokemon_species.json"),
                        PokemonSpecies.class);

        Pokemon pokemon = new Pokemon();
        List<PokeDescription> pokeDescriptions = new ArrayList<>();
        List<PokeGenus> pokeGenus = new ArrayList<>();

        Mockito.when(pokemonBuilder.getPokeDetail(id)).thenReturn(pokeDetail);
        Mockito.when(pokemonBuilder
                .getPokemonSpecies(pokeDetail.getSpecies().getName())).thenReturn(pokemonSpecies);
        Mockito.when(pokemonBuilder.getPokemon(pokeDetail)).thenReturn(pokemon);
        Mockito.when(pokemonBuilder
                .getPokemonEvolutions(pokemonSpecies.getEvolutionId())).thenReturn(new ArrayList<>());
        Mockito.when(pokemonBuilder
                .getPokemonDescriptions(pokeDescriptions)).thenReturn(new ArrayList<>());
        Mockito.when(pokemonBuilder.getPokemonGenus(pokeGenus)).thenReturn("Mouse Pokémon");

        PokemonDetail pokemonDetail = pokemonService.getPokemon(id);
        Assertions.assertNotNull(pokemonDetail);
    }

}
