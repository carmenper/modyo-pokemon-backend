package com.cemp.modyo.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PokeSpecies {

    @JsonProperty("evolution_chain")
    private PokeNameUrl evolutionChain;
    @JsonProperty("evolves_from_species")
    private PokeNameUrl evolvesFromSpecies;
    @JsonProperty("flavor_text_entries")
    private List<PokeDescription> descriptions;
    private PokeNameUrl color;
    private PokeNameUrl shape;
    private PokeNameUrl habitat;
    private PokeNameUrl generation;
    private List<PokeGenus> genera;

}
