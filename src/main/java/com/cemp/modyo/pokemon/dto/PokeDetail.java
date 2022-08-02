package com.cemp.modyo.pokemon.dto;

import lombok.Data;

import java.util.List;

@Data
public class PokeDetail {

    private List<PokeAbility> abilities;
    private Integer weight;
    private List<PokeType> types;
    private PokeSprites sprites;
    private PokeNameUrl species;
    private String name;

}
