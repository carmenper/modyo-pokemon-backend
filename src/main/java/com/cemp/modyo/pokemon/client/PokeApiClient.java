package com.cemp.modyo.pokemon.client;

import com.cemp.modyo.pokemon.dto.Poke;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.dto.PokeEvolution;
import com.cemp.modyo.pokemon.dto.PokeSpecies;
import reactor.core.publisher.Mono;

public interface PokeApiClient {

    Mono<Poke> retrievePoke(Integer offset, Integer limit);
    Mono<PokeDetail> retrievePokeDetail(String id);
    Mono<PokeSpecies> retrievePokeSpecies(String id);
    Mono<PokeEvolution> retrievePokeEvolution(String id);

}
