package com.cemp.modyo.pokemon.client.impl;

import com.cemp.modyo.pokemon.client.PokeApiClient;
import com.cemp.modyo.pokemon.client.PokemonClient;
import com.cemp.modyo.pokemon.dto.Poke;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.dto.PokeEvolution;
import com.cemp.modyo.pokemon.dto.PokeSpecies;
import com.cemp.modyo.pokemon.generic.CustomOptional;
import com.cemp.modyo.pokemon.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PokemonClientImpl implements PokemonClient {

    @Autowired
    private PokeApiClient pokeApiClient;

    @Override
    public Poke getPoke(Integer offset, Integer limit) {
        Mono<Poke> pokeMono = pokeApiClient.retrievePoke(offset, limit);
        Poke poke = pokeMono.block();
        ValidatorUtil.validatePoke(CustomOptional.ofNullable(poke).orElseNull());
        return poke;
    }

    @Override
    public PokeDetail getPokeDetail(String id) {
        Mono<PokeDetail> pokeDetailMono = pokeApiClient.retrievePokeDetail(id);
        PokeDetail pokeDetail = pokeDetailMono.block();
        ValidatorUtil.validatePokeDetail(CustomOptional.ofNullable(pokeDetail).orElseNull());
        return pokeDetail;
    }

    @Override
    public PokeSpecies getPokeSpecies(String id) {
        Mono<PokeSpecies> pokeSpeciesMono = pokeApiClient
                .retrievePokeSpecies(id);
        PokeSpecies pokeSpecies = pokeSpeciesMono.block();
        ValidatorUtil.validatePokeSpecies(CustomOptional.ofNullable(pokeSpecies).orElseNull());
        return pokeSpecies;
    }

    @Override
    public PokeEvolution getPokeEvolution(String id) {
        Mono<PokeEvolution> pokeEvolutionMono = pokeApiClient.retrievePokeEvolution(id);
        PokeEvolution pokeEvolution = pokeEvolutionMono.block();
        ValidatorUtil.validatePokeEvolution(CustomOptional.ofNullable(pokeEvolution).orElseNull());
        return pokeEvolution;
    }
}
