package com.cemp.modyo.pokemon.util;

import com.cemp.modyo.pokemon.dto.PokeChain;

import java.util.List;

public class ChainUtil {

    private ChainUtil() {
        // This is intentional
    }

    public static void processChain(List<String> pokemonEvolutions,
                              PokeChain chain) {
        pokemonEvolutions.add(chain.getSpecies().getName());
        processChainList(pokemonEvolutions, chain.getEvolvesTo());
    }

    private static void processChainList(List<String> pokemonEvolutions,
                                  List<PokeChain> chains) {
        chains.forEach(c -> processChain(pokemonEvolutions, c));
    }
}
