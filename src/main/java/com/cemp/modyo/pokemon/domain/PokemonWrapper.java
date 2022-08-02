package com.cemp.modyo.pokemon.domain;

import com.cemp.modyo.pokemon.dto.PokeMarker;
import lombok.Data;

import java.util.List;

@Data
public class PokemonWrapper {

    private List<Pokemon> pokemons;
    private Integer count;
    private PokeMarker next;
    private PokeMarker previous;
}
