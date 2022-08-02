package com.cemp.modyo.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PokeDescription {

    @JsonProperty("flavor_text")
    private String text;
    private PokeNameUrl language;
    private PokeNameUrl version;

}
