package com.cemp.modyo.pokemon.domain;

import lombok.Data;

import java.util.List;

@Data
public class PokemonDetail {

    private Pokemon pokemon;
    private List<PokemonDescription> descriptions;
    private List<String> evolutions;
    private String evolvedFrom;
    private String color;
    private String shape;
    private String habitat;
    private String generation;
    private String genus;

}
