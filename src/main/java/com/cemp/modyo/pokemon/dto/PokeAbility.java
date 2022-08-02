package com.cemp.modyo.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties({"hidden"})
public class PokeAbility {

    private PokeNameUrl ability;
    @JsonProperty("is_hidden")
    private Boolean isHidden;

}
