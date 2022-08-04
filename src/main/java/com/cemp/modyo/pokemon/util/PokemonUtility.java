package com.cemp.modyo.pokemon.util;

import com.cemp.modyo.pokemon.dto.PokeChain;
import com.cemp.modyo.pokemon.dto.PokeDescription;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PokemonUtility {

    private static final String OFFSET = "offset";
    private static final String LIMIT = "limit";

    public List<String> getAbilities(PokeDetail pokeDetail) {
        return pokeDetail.
                getAbilities().
                stream()
                .map(ability -> ability.getAbility().getName())
                .collect(Collectors.toList());
    }

    public List<String> getTypes(PokeDetail pokeDetail) {
        return pokeDetail.
                getTypes().
                stream()
                .map(type -> type.getType().getName())
                .collect(Collectors.toList());
    }

    public void addDescription(Map<String, List<String>> holder, PokeDescription desc) {
        List<String> versions;
        if (holder.containsKey(desc.getText())) {
            versions = holder.get(desc.getText());

        } else {
            versions = new ArrayList<>();
        }
        versions.add(desc.getVersion().getName());
        holder.put(desc.getText(), versions);
    }

    public List<String> getEvolutionChain(PokeChain chain) {
        List<String> pokemonEvolutions = new ArrayList<>();
        processChain(pokemonEvolutions, chain);
        return pokemonEvolutions;
    }

    private void processChain(List<String> pokemonEvolutions,
                             PokeChain chain) {
        pokemonEvolutions.add(chain.getSpecies().getName());
        processChainList(pokemonEvolutions, chain.getEvolvesTo());
    }

    private void processChainList(List<String> pokemonEvolutions,
                                 List<PokeChain> chains) {
        chains.forEach(c -> processChain(pokemonEvolutions, c));
    }


    public Integer getOffset(Map<String, Integer> map) {
        if (map != null && map.containsKey(OFFSET)) {
            return map.get(OFFSET);
        }
        return null;
    }

    public Integer getLimit(Map<String, Integer> map) {
        if (map != null && map.containsKey(LIMIT)) {
            return map.get(LIMIT);
        }
        return null;
    }

}
