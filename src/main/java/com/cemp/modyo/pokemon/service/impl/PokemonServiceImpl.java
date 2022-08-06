package com.cemp.modyo.pokemon.service.impl;

import com.cemp.modyo.pokemon.client.PokemonClient;
import com.cemp.modyo.pokemon.domain.Pokemon;
import com.cemp.modyo.pokemon.domain.PokemonDetail;
import com.cemp.modyo.pokemon.domain.PokemonWrapper;
import com.cemp.modyo.pokemon.dto.Poke;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.generic.CustomOptional;
import com.cemp.modyo.pokemon.pojo.PokemonSpecies;
import com.cemp.modyo.pokemon.service.PokemonService;
import com.cemp.modyo.pokemon.util.PokemonBuilder;
import com.cemp.modyo.pokemon.util.PokemonUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        PokemonWrapper pokemonWrapper = PokemonUtility.getPokemonWrapper(poke);
        List<Pokemon> pokemons = pokemonBuilder.getPokemonList(poke);
        pokemonWrapper.setPokemons(pokemons);
        return pokemonWrapper;
    }

    @Override
    public PokemonDetail getPokemon(String id) {

        PokeDetail pokeDetail = pokemonBuilder.getPokeDetail(id);
        PokemonSpecies pokemonSpecies = pokemonBuilder
                .getPokemonSpecies(pokeDetail.getSpecies().getName());

        PokemonDetail pokemonDetail = new PokemonDetail();
        pokemonDetail.setPokemon(pokemonBuilder.getPokemon(pokeDetail));
        pokemonDetail.setEvolutions(pokemonBuilder
                .getPokemonEvolutions(pokemonSpecies.getEvolutionId()));
        pokemonDetail.setDescriptions(pokemonBuilder
                .getPokemonDescriptions(pokemonSpecies.getDescriptions()));
        pokemonDetail.setGenus(pokemonBuilder.getPokemonGenus(pokemonSpecies.getGenera()));
        pokemonDetail.setColor(pokemonSpecies.getColor());
        pokemonDetail.setShape(pokemonSpecies.getShape());
        pokemonDetail.setEvolvedFrom(pokemonSpecies.getEvolvesFromSpecies());
        pokemonDetail.setHabitat(pokemonSpecies.getHabitat());
        pokemonDetail.setGeneration(pokemonSpecies.getGeneration());
        return pokemonDetail;
    }
}
