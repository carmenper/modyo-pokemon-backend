package com.cemp.modyo.pokemon.domain;

import com.cemp.modyo.pokemon.dto.PokeMarker;
import com.cemp.modyo.pokemon.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DomainTest {

    @Test
    void testErrorResponse() {

        ErrorResponse e1 = new ErrorResponse(1, DateUtil.getTimeStamp());
        Assertions.assertNotNull(e1.getTimestamp());
        Assertions.assertTrue(e1.getId() > 0);
        Assertions.assertNotNull(e1.getDetail());

        ErrorResponse e2 = new ErrorResponse(1, "detail", DateUtil.getTimeStamp());
        Assertions.assertNotNull(e2.getTimestamp());
        Assertions.assertTrue(e2.getId() > 0);
        Assertions.assertNotNull(e2.getDetail());
    }

    @Test
    void testPokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setPhoto("");
        pokemon.setName("");
        pokemon.setWeight(1);
        pokemon.setAbilities(new ArrayList<>());
        pokemon.setTypes(new ArrayList<>());

        pokemon.getPhoto();
        pokemon.getName();
        pokemon.getWeight();
        pokemon.getAbilities();
        pokemon.getTypes();

        Pokemon p = new Pokemon("pokemon");
        Assertions.assertNotNull(p);

    }

    @Test
    void testPokemonDescription() {

        PokemonDescription pokemonDescription = new PokemonDescription("", new ArrayList<>());
        pokemonDescription.setDescription("");
        pokemonDescription.setVersions(new ArrayList<>());
        pokemonDescription.getDescription();
        pokemonDescription.getVersions();
        Assertions.assertNotNull(pokemonDescription);

    }

    @Test
    void testPokemonDetail() {
        PokemonDetail pokemonDetail = new PokemonDetail();

        pokemonDetail.setPokemon(new Pokemon());
        pokemonDetail.setDescriptions(new ArrayList<>());
        pokemonDetail.setColor("");
        pokemonDetail.setEvolutions(new ArrayList<>());
        pokemonDetail.setGeneration("");
        pokemonDetail.setEvolvedFrom("");
        pokemonDetail.setGenus("");
        pokemonDetail.setHabitat("");
        pokemonDetail.setShape("");


        pokemonDetail.getPokemon();
        pokemonDetail.getDescriptions();
        pokemonDetail.getColor();
        pokemonDetail.getEvolutions();
        pokemonDetail.getGeneration();
        pokemonDetail.getEvolvedFrom();
        pokemonDetail.getGenus();
        pokemonDetail.getHabitat();
        pokemonDetail.getShape();
        Assertions.assertNotNull(pokemonDetail);

    }

    @Test
    void testPokemonErrorResponse() {
        PokemonErrorResponse pokemonErrorResponse = new PokemonErrorResponse(1, "");
        pokemonErrorResponse.getError();
        Assertions.assertNotNull(pokemonErrorResponse);
    }

    @Test
    void testPokemonEvolution() {
        PokemonEvolution pokemonEvolution = new PokemonEvolution();

        pokemonEvolution.setEvolution(new ArrayList<>());
        pokemonEvolution.setEvolvesFromSpecies("");

        pokemonEvolution.getEvolution();
        pokemonEvolution.getEvolvesFromSpecies();
        Assertions.assertNotNull(pokemonEvolution);
    }

    @Test
    void testPokemonWrapper() {
        PokemonWrapper pokemonWrapper = new PokemonWrapper();

        pokemonWrapper.setPokemons(new ArrayList<>());
        pokemonWrapper.setNext(new PokeMarker(0, 0));
        pokemonWrapper.setPrevious(new PokeMarker(0, 0));
        pokemonWrapper.setCount(123);

        pokemonWrapper.getPokemons();
        pokemonWrapper.getNext();
        pokemonWrapper.getPrevious();
        pokemonWrapper.getCount();
        Assertions.assertNotNull(pokemonWrapper);
    }

}
