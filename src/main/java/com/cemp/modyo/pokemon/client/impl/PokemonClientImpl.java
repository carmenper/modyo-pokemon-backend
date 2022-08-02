package com.cemp.modyo.pokemon.client.impl;

import com.cemp.modyo.pokemon.client.PokemonClient;
import com.cemp.modyo.pokemon.dto.Poke;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.dto.PokeEvolution;
import com.cemp.modyo.pokemon.dto.PokeSpecies;
import com.cemp.modyo.pokemon.pojo.CustomOptional;
import com.cemp.modyo.pokemon.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class PokemonClientImpl implements PokemonClient {

    private static final String ID = "/{id}";
    @Value("${poke-api.methods.get-poke:/pokemon}")
    private String pokeUri;
    @Value("${poke-api.methods.get-species:/pokemon-species}")
    private String speciesUri;
    @Value("${poke-api.methods.get-evolution:/evolution-chain}")
    private String evolutionUri;
    @Autowired
    private WebClient webClient;

    @Cacheable
    @Override
    public Poke getPoke(Integer offset, Integer limit) {
        Mono<Poke> response = webClient
                .get()
                .uri(builder -> builder
                        .path(pokeUri)
                        .queryParamIfPresent("offset", Optional.ofNullable(offset))
                        .queryParamIfPresent("limit", Optional.ofNullable(limit))
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Poke.class);

        Poke poke = response.block();
        ValidatorUtil.validatePoke(CustomOptional.ofNullable(poke).orElseNull());
        return poke;
    }

    @Cacheable
    @Override
    public PokeDetail getPokemon(String id) {
        Mono<PokeDetail> response = webClient
                .get()
                .uri(builder -> builder
                        .path(pokeUri + ID)
                        .build(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PokeDetail.class);

        PokeDetail pokeDetail = response.block();
        ValidatorUtil.validatePokeDetail(CustomOptional.ofNullable(pokeDetail).orElseNull());
        return pokeDetail;
    }

    @Cacheable
    @Override
    public PokeSpecies getSpecies(String id) {
        Mono<PokeSpecies> response = webClient
                .get()
                .uri(builder -> builder
                        .path(speciesUri + ID)
                        .build(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PokeSpecies.class);

        PokeSpecies pokeSpecies = response.block();
        ValidatorUtil.validatePokeSpecies(CustomOptional.ofNullable(pokeSpecies).orElseNull());
        return pokeSpecies;
    }

    @Cacheable
    @Override
    public PokeEvolution getEvolution(String id) {
        Mono<PokeEvolution> response = webClient
                .get()
                .uri(builder -> builder
                        .path(evolutionUri + ID)
                        .build(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PokeEvolution.class);
        PokeEvolution pokeEvolution = response.block();
        ValidatorUtil.validatePokeEvolution(CustomOptional.ofNullable(pokeEvolution).orElseNull());
        return pokeEvolution;
    }
}
