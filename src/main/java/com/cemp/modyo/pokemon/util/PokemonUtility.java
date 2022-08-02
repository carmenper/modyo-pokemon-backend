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

    public void processChainList(List<String> pokemonEvolutions,
                                 List<PokeChain> chains) {
        chains.forEach(c -> processChain(pokemonEvolutions, c));
    }

    public void processChain(List<String> pokemonEvolutions,
                             PokeChain chain) {
        pokemonEvolutions.add(chain.getSpecies().getName());
        processChainList(pokemonEvolutions, chain.getEvolvesTo());
    }
}
