package com.cemp.modyo.pokemon.pojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class PokemonSpeciesTest {

    @Test
    void testPokemonSpecies() {
        PokemonSpecies pokemonSpecies = new PokemonSpecies();

        pokemonSpecies.setColor("");
        pokemonSpecies.setDescriptions(new ArrayList<>());
        pokemonSpecies.setEvolvesFromSpecies("");
        pokemonSpecies.setEvolutionId("");
        pokemonSpecies.setGenera(new ArrayList<>());
        pokemonSpecies.setGeneration("");
        pokemonSpecies.setGenus("");
        pokemonSpecies.setHabitat("");
        pokemonSpecies.setShape("");

        pokemonSpecies.getColor();
        pokemonSpecies.getDescriptions();
        pokemonSpecies.getEvolvesFromSpecies();
        pokemonSpecies.getEvolutionId();
        pokemonSpecies.getGenera();
        pokemonSpecies.getGeneration();
        pokemonSpecies.getGenus();
        pokemonSpecies.getHabitat();
        pokemonSpecies.getShape();

        Assertions.assertNotNull(pokemonSpecies);
    }
}
