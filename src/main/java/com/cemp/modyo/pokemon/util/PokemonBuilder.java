package com.cemp.modyo.pokemon.util;

import com.cemp.modyo.pokemon.client.PokemonClient;
import com.cemp.modyo.pokemon.domain.Pokemon;
import com.cemp.modyo.pokemon.domain.PokemonDescription;
import com.cemp.modyo.pokemon.dto.*;
import com.cemp.modyo.pokemon.pojo.PokemonSpecies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PokemonBuilder {

    private final PokemonClient pokemonClient;

    public List<Pokemon> getPokemonList(Poke poke) {
        return poke.getResults().stream()
                .filter(p -> p != null && p.getName() != null && !p.getName().isBlank())
                .map(p -> getPokemon(getPokeDetail(p.getName())))
                .collect(Collectors.toList());
    }

    public Pokemon getPokemon(PokeDetail pokeDetail) {
        Pokemon pokemon = new Pokemon(pokeDetail.getName());
        pokemon.setWeight(pokeDetail.getWeight());
        pokemon.setTypes(PokemonUtility.getTypes(pokeDetail));
        pokemon.setAbilities(PokemonUtility.getAbilities(pokeDetail));
        pokemon.setPhoto(pokeDetail.getSprites().getFrontDefault());
        return pokemon;
    }

    public PokeDetail getPokeDetail(String id) {
        return pokemonClient.getPokeDetail(id);
    }

    public PokemonSpecies getPokemonSpecies(String id) {
        PokeDetail pokeDetail = getPokeDetail(id);

        PokeSpecies pokeSpecies = pokemonClient.getPokeSpecies(pokeDetail.getSpecies().getName());

        PokemonSpecies pokemonSpecies = new PokemonSpecies();
        pokemonSpecies.setColor(pokeSpecies.getColor().getName());
        pokemonSpecies.setShape(pokeSpecies.getShape().getName());
        pokemonSpecies.setEvolvesFromSpecies(pokeSpecies.getEvolvesFromSpecies().getName());
        pokemonSpecies.setHabitat(pokeSpecies.getHabitat().getName());
        pokemonSpecies.setGeneration(pokeSpecies.getGeneration().getName());

        pokemonSpecies.setEvolutionId(StringUtil
                .getEvolutionId(pokeSpecies.getEvolutionChain().getUrl()));

        pokemonSpecies.setDescriptions(pokeSpecies.getDescriptions());
        pokemonSpecies.setGenera(pokeSpecies.getGenera());

        return pokemonSpecies;
    }

    public List<PokemonDescription> getPokemonDescriptions(List<PokeDescription> descriptions) {

        Map<String, List<String>> holder = new HashMap<>();
        descriptions.stream()
                .filter(d -> d.getLanguage().getName().equals("en"))
                .forEach(d -> PokemonUtility.addDescription(holder, d));

        return holder.keySet().stream()
                .map(k -> new PokemonDescription(k, holder.get(k)))
                .collect(Collectors.toList());
    }

    public String getPokemonGenus(List<PokeGenus> genera) {
        return genera.stream()
                .filter(g -> g.getLanguage().getName().equalsIgnoreCase("en"))
                .findFirst().map(PokeGenus::getGenus).orElse(null);
    }

    public List<String> getPokemonEvolutions(String id) {

        PokeEvolution pokeEvolution = pokemonClient.getPokeEvolution(id);

        return PokemonUtility.getEvolutionChain(pokeEvolution.getChain());
    }



}
