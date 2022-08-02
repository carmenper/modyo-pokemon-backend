package com.cemp.modyo.pokemon.domain;

import lombok.Data;

import java.util.List;

@Data
public class PokemonEvolution {

    private String evolvesFromSpecies;
    private List<String> evolution;

}
