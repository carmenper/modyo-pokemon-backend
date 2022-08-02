package com.cemp.modyo.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PokeSprites {

    @JsonProperty("front_default")
    private String frontDefault;

}
