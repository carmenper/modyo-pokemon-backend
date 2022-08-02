package com.cemp.modyo.pokemon.util;

import com.cemp.modyo.pokemon.dto.Poke;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.dto.PokeEvolution;
import com.cemp.modyo.pokemon.dto.PokeSpecies;
import com.cemp.modyo.pokemon.enums.ExceptionEnum;
import com.cemp.modyo.pokemon.exception.ApplicationException;
import org.springframework.util.Assert;

public class ValidatorUtil {

    private ValidatorUtil() {
        // This is intentional
    }

    public static void validateParams(Integer offset, Integer limit) {
        try {
            Assert.isTrue(offset != null ^ limit == null, "");
        } catch (IllegalArgumentException e) {
            throw new ApplicationException(ExceptionEnum.INVALID_PARAMS);
        }
    }

    public static void validatePoke(Poke o) {
        try {
            Assert.notNull(o, "Poke null");
            Assert.notNull(o.getCount(), "Poke count null");
            Assert.notNull(o.getResults(), "Poke results null");
            Assert.notEmpty(o.getResults(), "Poke results empty");
            Assert.noNullElements(o.getResults(), "Poke results element null");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new ApplicationException(ExceptionEnum.INVALID_DATA);
        }
    }

    public static void validatePokeDetail(PokeDetail o) {
        try {
            Assert.notNull(o, "PokeDetail null");
            Assert.notNull(o.getName(), "PokeDetail name null");
            Assert.notNull(o.getWeight(), "PokeDetail weight null");

            Assert.notNull(o.getSprites(), "PokeDetail sprites null");
            Assert.notNull(o.getSprites().getFrontDefault(), "PokeDetail text null");

            Assert.notNull(o.getAbilities(), "PokeDetail abilities null");
            Assert.notEmpty(o.getAbilities(), "PokeDetail abilities empty");
            Assert.noNullElements(o.getAbilities(), "PokeDetail abilities element null");

            Assert.notNull(o.getTypes(), "PokeDetail types null");
            Assert.notEmpty(o.getTypes(), "PokeDetail types empty");
            Assert.noNullElements(o.getTypes(), "PokeDetail types element null");

            Assert.notNull(o.getSpecies(), "PokeDetail species null");
            Assert.notNull(o.getSpecies().getName(), "PokeDetail species name null");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new ApplicationException(ExceptionEnum.INVALID_DATA);
        }
    }

    public static void validatePokeSpecies(PokeSpecies o) {
        try {
            Assert.notNull(o, "PokeSpecies null");

            Assert.notNull(o.getEvolvesFromSpecies(), "PokeSpecies EvolvesFromSpecies null");
            Assert.notNull(o.getEvolvesFromSpecies().getName(), "PokeSpecies EvolvesFromSpecies name null");

            Assert.notNull(o.getColor(), "PokeSpecies color null");
            Assert.notNull(o.getColor().getName(), "PokeSpecies color name null");

            Assert.notNull(o.getGeneration(), "PokeSpecies generation null");
            Assert.notNull(o.getGeneration().getName(), "PokeSpecies generation name null");

            Assert.notNull(o.getHabitat(), "PokeSpecies habitat null");
            Assert.notNull(o.getHabitat().getName(), "PokeSpecies habitat name null");

            Assert.notNull(o.getShape(), "PokeSpecies shape null");
            Assert.notNull(o.getShape().getName(), "PokeSpecies shape name null");

            Assert.notNull(o.getEvolutionChain(), "PokeSpecies chain null");
            Assert.notNull(o.getEvolutionChain().getUrl(), "PokeSpecies chain url null");

            Assert.notNull(o.getGenera(), "PokeSpecies general null");
            Assert.notEmpty(o.getGenera(), "PokeSpecies general empty");
            Assert.noNullElements(o.getGenera(), "PokeSpecies general element null");

            Assert.notNull(o.getDescriptions(), "PokeSpecies descriptions null");
            Assert.notEmpty(o.getDescriptions(), "PokeSpecies descriptions empty");
            Assert.noNullElements(o.getDescriptions(), "PokeSpecies descriptions element null");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new ApplicationException(ExceptionEnum.INVALID_DATA);
        }
    }

    public static void validatePokeEvolution(PokeEvolution o) {
        try {
            Assert.notNull(o, "PokeEvolution null");

            Assert.notNull(o.getChain(), "PokeEvolution chain null");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new ApplicationException(ExceptionEnum.INVALID_DATA);
        }
    }

}
