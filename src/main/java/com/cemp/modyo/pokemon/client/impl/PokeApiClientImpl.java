package com.cemp.modyo.pokemon.client.impl;

import com.cemp.modyo.pokemon.client.PokeApiClient;
import com.cemp.modyo.pokemon.dto.Poke;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.dto.PokeEvolution;
import com.cemp.modyo.pokemon.dto.PokeSpecies;
import com.cemp.modyo.pokemon.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class PokeApiClientImpl implements PokeApiClient {

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
    public Mono<Poke> retrievePoke(Integer offset, Integer limit) {
        return webClient
                .get()
                .uri(builder -> builder
                        .path(pokeUri)
                        .queryParamIfPresent("offset", Optional.ofNullable(offset))
                        .queryParamIfPresent("limit", Optional.ofNullable(limit))
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Poke.class);
    }

    @Cacheable
    @Override
    public Mono<PokeDetail> retrievePokeDetail(String id) {
        return webClient
                .get()
                .uri(builder -> builder
                        .path(pokeUri + ID)
                        .build(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(hs -> hs == HttpStatus.NOT_FOUND,
                        r -> Mono.error(new NotFoundException(id)))
                .bodyToMono(PokeDetail.class);
    }

    @Cacheable
    @Override
    public Mono<PokeSpecies> retrievePokeSpecies(String id) {
        return webClient
                .get()
                .uri(builder -> builder
                        .path(speciesUri + ID)
                        .build(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PokeSpecies.class);
    }

    @Cacheable
    @Override
    public Mono<PokeEvolution> retrievePokeEvolution(String id) {
        return webClient
                .get()
                .uri(builder -> builder
                        .path(evolutionUri + ID)
                        .build(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PokeEvolution.class);
    }
}
