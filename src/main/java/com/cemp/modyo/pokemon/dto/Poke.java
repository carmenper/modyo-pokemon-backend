package com.cemp.modyo.pokemon.dto;

import lombok.Data;

import java.util.List;

@Data
public class Poke {

    private Integer count;
    private String next;
    private String previous;
    private List<PokeNameUrl> results;

}
