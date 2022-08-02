package com.cemp.modyo.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PokeChain {

    private PokeNameUrl species;
    @JsonProperty("evolves_to")
    private List<PokeChain> evolvesTo;
}
