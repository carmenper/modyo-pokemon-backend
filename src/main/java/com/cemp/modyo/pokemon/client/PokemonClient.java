package com.cemp.modyo.pokemon.client;

import com.cemp.modyo.pokemon.dto.Poke;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.dto.PokeEvolution;
import com.cemp.modyo.pokemon.dto.PokeSpecies;

public interface PokemonClient {

    Poke getPoke(Integer offset, Integer limit);
    PokeDetail getPokemon(String id);
    PokeSpecies getSpecies(String id);
    PokeEvolution getEvolution(String id);

}
