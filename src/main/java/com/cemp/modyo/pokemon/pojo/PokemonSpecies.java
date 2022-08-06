package com.cemp.modyo.pokemon.pojo;

import com.cemp.modyo.pokemon.dto.PokeDescription;
import com.cemp.modyo.pokemon.dto.PokeGenus;
import lombok.Data;

import java.util.List;

@Data
public class PokemonSpecies {

    private String color;
    private String shape;
    private String evolvesFromSpecies;
    private String habitat;
    private String generation;
    private String genus;
    private String evolutionId;
    private List<PokeDescription> descriptions;
    private List<PokeGenus> genera;

}
