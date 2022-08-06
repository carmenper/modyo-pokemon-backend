package com.cemp.modyo.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties({"hidden"})
public class PokeAbility {

    private PokeNameUrl ability;

}
