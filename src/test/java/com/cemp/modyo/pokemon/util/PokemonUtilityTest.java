package com.cemp.modyo.pokemon.util;

import com.cemp.modyo.pokemon.dto.PokeDescription;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.dto.PokeEvolution;
import com.cemp.modyo.pokemon.dto.PokeNameUrl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PokemonUtilityTest {

    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final PokemonUtility pokemonUtility = new PokemonUtility();

    @Test
    void getAbilitiesTest() throws IOException {
        PokeDetail pokeDetail = mapper
                .readValue(new File("src/test/resources/json/poke.json"), PokeDetail.class);
        List<String> pokemonAbilities = pokemonUtility.getAbilities(pokeDetail);
        Assertions.assertNotNull(pokemonAbilities);
        Assert.noNullElements(pokemonAbilities, "");
    }

    @Test
    void getTypesTest() throws IOException {
        PokeDetail pokeDetail = mapper
                .readValue(new File("src/test/resources/json/poke.json"), PokeDetail.class);
        List<String> pokemonTypes = pokemonUtility.getTypes(pokeDetail);
        Assertions.assertNotNull(pokemonTypes);
        Assert.noNullElements(pokemonTypes, "");
    }

    @Test
    void addDescription() {
        Map<String, List<String>> holder = new HashMap<>();
        PokeDescription desc1 = new PokeDescription();
        desc1.setText("Description Text");
        PokeNameUrl version1 = new PokeNameUrl();
        version1.setName("v1");
        desc1.setVersion(version1);

        PokeDescription desc2 = new PokeDescription();
        desc2.setText("Description Text");
        PokeNameUrl version2 = new PokeNameUrl();
        version1.setName("v2");
        desc2.setVersion(version1);

        pokemonUtility.addDescription(holder, desc1);
        pokemonUtility.addDescription(holder, desc2);

        Assertions.assertEquals(1, holder.size());
        List<String> vers = holder.get("Description Text");
        Assertions.assertEquals(2, vers.size());
    }

    @Test
    void processChain() throws IOException {
        PokeEvolution pokeEvolution = mapper
                .readValue(new File("src/test/resources/json/poke_evolution.json"), PokeEvolution.class);
        List<String> evolutions = pokemonUtility.getEvolutionChain(pokeEvolution.getChain());
        Assertions.assertEquals(3, evolutions.size());
    }

    @Test
    void getOffset() {
        Assertions.assertNull(pokemonUtility.getOffset(null));
        Map<String, Integer> map = new HashMap<>();
        map.put("offset", 1);
        Assertions.assertNotNull(pokemonUtility.getOffset(map));
    }

    @Test
    void getLimit() {
        Assertions.assertNull(pokemonUtility.getLimit(null));
        Map<String, Integer> map = new HashMap<>();
        map.put("limit", 1);
        Assertions.assertNotNull(pokemonUtility.getLimit(map));

    }

}
