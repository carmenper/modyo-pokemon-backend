package com.cemp.modyo.pokemon.service;

import com.cemp.modyo.pokemon.domain.PokemonDetail;
import com.cemp.modyo.pokemon.domain.PokemonWrapper;

public interface PokemonService {

    PokemonWrapper getPokemons(Integer offset, Integer limit);
    PokemonDetail getPokemon(String id);

}
