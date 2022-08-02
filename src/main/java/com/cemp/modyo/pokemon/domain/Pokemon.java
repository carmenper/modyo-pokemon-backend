package com.cemp.modyo.pokemon.domain;

import lombok.Data;

import java.util.List;

@Data
public class Pokemon {

    public Pokemon() {
        // This is intentional
    }

    public Pokemon(String name) {
        this.name = name;
    }

    private String name;
    private Integer weight;
    private List<String> types;
    private List<String> abilities;
    private String photo;

}
