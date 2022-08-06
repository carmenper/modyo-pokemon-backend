package com.cemp.modyo.pokemon.util;

import com.cemp.modyo.pokemon.domain.PokemonWrapper;
import com.cemp.modyo.pokemon.dto.Poke;
import com.cemp.modyo.pokemon.dto.PokeChain;
import com.cemp.modyo.pokemon.dto.PokeDescription;
import com.cemp.modyo.pokemon.dto.PokeDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokemonUtility {


    private PokemonUtility() {
        // This is intentional
    }

    public static PokemonWrapper getPokemonWrapper(Poke poke) {
        PokemonWrapper pokemonWrapper = new PokemonWrapper();

        pokemonWrapper.setCount(poke.getCount());

        if (poke.getNext() != null && !poke.getNext().isBlank()) {
            pokemonWrapper.setNext(QueryUtil.getPokeMarker(poke.getNext()));
        }
        if (poke.getPrevious() != null && !poke.getPrevious().isBlank()) {
            pokemonWrapper.setPrevious(QueryUtil.getPokeMarker(poke.getPrevious()));
        }
        return pokemonWrapper;
    }

    public static List<String> getAbilities(PokeDetail pokeDetail) {
        return pokeDetail.
                getAbilities().
                stream()
                .map(ability -> ability.getAbility().getName())
                .collect(Collectors.toList());
    }

    public static List<String> getTypes(PokeDetail pokeDetail) {
        return pokeDetail.
                getTypes().
                stream()
                .map(type -> type.getType().getName())
                .collect(Collectors.toList());
    }

    public static void addDescription(Map<String, List<String>> holder, PokeDescription desc) {
        List<String> versions;
        if (holder.containsKey(desc.getText())) {
            versions = holder.get(desc.getText());

        } else {
            versions = new ArrayList<>();
        }
        versions.add(desc.getVersion().getName());
        holder.put(desc.getText(), versions);
    }

    public static List<String> getEvolutionChain(PokeChain chain) {
        List<String> pokemonEvolutions = new ArrayList<>();
        ChainUtil.processChain(pokemonEvolutions, chain);
        return pokemonEvolutions;
    }


}
