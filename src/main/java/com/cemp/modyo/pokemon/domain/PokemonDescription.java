package com.cemp.modyo.pokemon.domain;

import lombok.Data;

import java.util.List;

@Data
public class PokemonDescription {

    public PokemonDescription(String description, List<String> versions) {
        this.description = description;
        this.versions = versions;
    }

    private String description;
    private List<String> versions;

}
