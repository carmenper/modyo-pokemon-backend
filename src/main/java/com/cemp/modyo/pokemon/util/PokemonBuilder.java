package com.cemp.modyo.pokemon.util;

import com.cemp.modyo.pokemon.domain.Pokemon;
import com.cemp.modyo.pokemon.domain.PokemonDescription;
import com.cemp.modyo.pokemon.dto.*;
import com.cemp.modyo.pokemon.enums.ExceptionEnum;
import com.cemp.modyo.pokemon.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PokemonBuilder {

    @Autowired
    private PokemonUtility pokemonUtility;

    public PokeMarker getPokeMarker(String url) {
        try {
            Map<String, Integer> map = QueryUtil.getQueryMap(new URL(url));
            return new PokeMarker(pokemonUtility.getOffset(map),
                    pokemonUtility.getLimit(map));
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public Pokemon getPokemon(PokeDetail pokeDetail) {
        if (pokeDetail != null) {
            Pokemon pokemon = new Pokemon(pokeDetail.getName());
            pokemon.setWeight(pokeDetail.getWeight());
            pokemon.setTypes(pokemonUtility.getTypes(pokeDetail));
            pokemon.setAbilities(pokemonUtility.getAbilities(pokeDetail));
            pokemon.setPhoto(pokeDetail.getSprites().getFrontDefault());
            return pokemon;
        }
        throw new ApplicationException(ExceptionEnum.INVALID_ID);
    }

    public List<PokemonDescription> getPokemonDescriptions(PokeSpecies species) {
        Map<String, List<String>> holder = new HashMap<>();
        species.getDescriptions().stream()
                .filter(d -> d.getLanguage().getName().equals("en"))
                .forEach(d -> pokemonUtility.addDescription(holder, d));

        return holder.keySet().stream()
                .map(k -> new PokemonDescription(k, holder.get(k)))
                .collect(Collectors.toList());
    }

    public String getGenus(PokeSpecies species) {
        return species.getGenera().stream()
                .filter(g -> g.getLanguage().getName().equalsIgnoreCase("en"))
                .findFirst().map(PokeGenus::getGenus).orElse(null);
    }

    public List<String> getPokemonEvolutions(PokeChain chain) {
        return pokemonUtility.getEvolutionChain(chain);
    }



}
