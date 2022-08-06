package com.cemp.modyo.pokemon.client;

import com.cemp.modyo.pokemon.dto.Poke;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.dto.PokeEvolution;
import com.cemp.modyo.pokemon.dto.PokeSpecies;

public interface PokemonClient {

    Poke getPoke(Integer offset, Integer limit);
    PokeDetail getPokeDetail(String id);
    PokeSpecies getPokeSpecies(String id);
    PokeEvolution getPokeEvolution(String id);

}
