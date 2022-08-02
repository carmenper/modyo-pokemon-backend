package com.cemp.modyo.pokemon.service.impl;

import com.cemp.modyo.pokemon.client.PokemonClient;
import com.cemp.modyo.pokemon.domain.PokemonDetail;
import com.cemp.modyo.pokemon.domain.PokemonWrapper;
import com.cemp.modyo.pokemon.dto.*;
import com.cemp.modyo.pokemon.pojo.CustomOptional;
import com.cemp.modyo.pokemon.service.PokemonService;
import com.cemp.modyo.pokemon.util.PokemonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonClient pokemonClient;

    @Autowired
    private PokemonBuilder pokemonBuilder;

    @Override
    public PokemonWrapper getPokemons(Integer offset, Integer limit) {

        Poke poke = pokemonClient.getPoke(CustomOptional.ofNullable(offset).orElseNull(),
                CustomOptional.ofNullable(limit).orElseNull());

        PokemonWrapper pokemonWrapper = new PokemonWrapper();
        pokemonWrapper.setCount(poke.getCount());

        pokemonWrapper.setPokemons(poke.getResults().stream()
                .map(p -> pokemonBuilder
                        .getPokemon(pokemonClient
                                .getPokemon(p.getName())))
                .collect(Collectors.toList()));

        if (poke.getNext() != null && !poke.getNext().isBlank()) {
            pokemonWrapper.setNext(pokemonBuilder.getPokeMarker(poke.getNext()));
        }
        if (poke.getPrevious() != null && !poke.getPrevious().isBlank()) {
            pokemonWrapper.setPrevious(pokemonBuilder.getPokeMarker(poke.getPrevious()));
        }

        return pokemonWrapper;
    }

    @Override
    public PokemonDetail getPokemon(String id) {
        PokemonDetail pokemonDetail = new PokemonDetail();

        PokeDetail pokeDetail = pokemonClient.getPokemon(id);

        pokemonDetail.setPokemon(pokemonBuilder.getPokemon(pokeDetail));

        PokeSpecies species = pokemonClient.getSpecies(pokeDetail.getSpecies().getName());

        String evolutionId = species.getEvolutionChain().getUrl()
                .replace("https://pokeapi.co/api/v2/evolution-chain/", "")
                .replace("/", "");


        PokeEvolution evolution = pokemonClient.getEvolution(evolutionId);

        pokemonDetail.setEvolutions(pokemonBuilder.getPokemonEvolutions(evolution.getChain()));

        pokemonDetail.setDescriptions(pokemonBuilder.getPokemonDescriptions(species));
        pokemonDetail.setColor(species.getColor().getName());
        pokemonDetail.setShape(species.getShape().getName());
        pokemonDetail.setEvolvedFrom(species.getEvolvesFromSpecies().getName());
        pokemonDetail.setHabitat(species.getHabitat().getName());
        pokemonDetail.setGeneration(species.getGeneration().getName());
        pokemonDetail.setGenus(pokemonBuilder.getGenus(species));
        return pokemonDetail;
    }

    public void processChainList(List<PokeChain> chains) {
        for (PokeChain chain : chains) {
            processChain(chain);
        }
    }

    public void processChain(PokeChain chain) {
        System.out.println("Species");
        System.out.println(chain.getSpecies().getName());
        processChainList(chain.getEvolvesTo());
    }
}
