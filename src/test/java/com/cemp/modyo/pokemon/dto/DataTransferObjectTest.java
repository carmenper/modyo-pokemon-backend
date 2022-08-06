package com.cemp.modyo.pokemon.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DataTransferObjectTest {

    @Test
    void testPoke(){
        Poke poke = new Poke();
        poke.setNext("");
        poke.setPrevious("");
        poke.setCount(1);
        poke.setResults(new ArrayList<>());
        poke.getNext();
        poke.getPrevious();
        poke.getCount();
        poke.getResults();
        Assertions.assertNotNull(poke);
    }

    @Test
    void testPokeAbility(){
        PokeAbility pokeAbility = new PokeAbility();
        pokeAbility.setAbility(new PokeNameUrl());
        pokeAbility.getAbility();
        Assertions.assertNotNull(pokeAbility);
    }

    @Test
    void testPokeChain(){
        PokeChain pokeChain = new PokeChain();
        pokeChain.setSpecies(new PokeNameUrl());
        pokeChain.setEvolvesTo(new ArrayList<>());
        pokeChain.getSpecies();
        pokeChain.getEvolvesTo();
        Assertions.assertNotNull(pokeChain);
    }

    @Test
    void testPokeDescription(){
        PokeDescription pokeDescription = new PokeDescription();
        pokeDescription.setText("");
        pokeDescription.setLanguage(new PokeNameUrl());
        pokeDescription.setVersion(new PokeNameUrl());
        pokeDescription.getText();
        pokeDescription.getLanguage();
        pokeDescription.getVersion();
        Assertions.assertNotNull(pokeDescription);
    }

    @Test
    void testPokeDetail(){
        PokeDetail pokeDetail = new PokeDetail();
        pokeDetail.setName("");
        pokeDetail.setAbilities(new ArrayList<>());
        pokeDetail.setTypes(new ArrayList<>());
        pokeDetail.setSpecies(new PokeNameUrl());
        pokeDetail.setSprites(new PokeSprites());
        pokeDetail.setWeight(1);

        pokeDetail.getName();
        pokeDetail.getAbilities();
        pokeDetail.getTypes();
        pokeDetail.getSpecies();
        pokeDetail.getSprites();
        pokeDetail.getWeight();
        Assertions.assertNotNull(pokeDetail);
    }

    @Test
    void testPokeEvolution(){
        PokeEvolution pokeEvolution = new PokeEvolution();
        pokeEvolution.setChain(new PokeChain());
        pokeEvolution.getChain();
        Assertions.assertNotNull(pokeEvolution);
    }

    @Test
    void testPokeGenus(){
        PokeGenus pokeGenus = new PokeGenus();
        pokeGenus.setGenus("");
        pokeGenus.setLanguage(new PokeNameUrl());
        pokeGenus.getGenus();
        pokeGenus.getLanguage();
        Assertions.assertNotNull(pokeGenus);
    }

    @Test
    void testPokeMarker(){
        PokeMarker pokeMarker = new PokeMarker(0 , 0);
        pokeMarker.getLimit();
        pokeMarker.getOffset();
        pokeMarker.setLimit(1);
        pokeMarker.setOffset(1);
        Assertions.assertNotNull(pokeMarker);
    }

    @Test
    void testPokeNameUrl(){
        PokeNameUrl pokeNameUrl = new PokeNameUrl();
        pokeNameUrl.setName("");
        pokeNameUrl.setUrl("");
        pokeNameUrl.getName();
        pokeNameUrl.getUrl();
        Assertions.assertNotNull(pokeNameUrl);
    }

    @Test
    void testPokeSpecies(){
        PokeSpecies pokeSpecies = new PokeSpecies();

        pokeSpecies.setEvolvesFromSpecies(new PokeNameUrl());
        pokeSpecies.setDescriptions(new ArrayList<>());
        pokeSpecies.setGenera(new ArrayList<>());
        pokeSpecies.setEvolutionChain(new PokeNameUrl());
        pokeSpecies.setColor(new PokeNameUrl());
        pokeSpecies.setGeneration(new PokeNameUrl());
        pokeSpecies.setHabitat(new PokeNameUrl());
        pokeSpecies.setShape(new PokeNameUrl());

        pokeSpecies.getEvolvesFromSpecies();
        pokeSpecies.getDescriptions();
        pokeSpecies.getGenera();
        pokeSpecies.getEvolutionChain();
        pokeSpecies.getColor();
        pokeSpecies.getGeneration();
        pokeSpecies.getHabitat();
        pokeSpecies.getShape();
        Assertions.assertNotNull(pokeSpecies);
    }

    @Test
    void testPokeSprites(){
        PokeSprites pokeSprites = new PokeSprites();
        pokeSprites.setFrontDefault("");
        pokeSprites.getFrontDefault();
        Assertions.assertNotNull(pokeSprites);
    }

    @Test
    void testPokeType(){
        PokeType pokeType = new PokeType();
        pokeType.setType(new PokeNameUrl());
        pokeType.getType();
        Assertions.assertNotNull(pokeType);
    }

}
