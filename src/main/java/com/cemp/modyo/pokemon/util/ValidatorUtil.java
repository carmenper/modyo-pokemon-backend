package com.cemp.modyo.pokemon.util;

import com.cemp.modyo.pokemon.dto.Poke;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.dto.PokeEvolution;
import com.cemp.modyo.pokemon.dto.PokeSpecies;
import com.cemp.modyo.pokemon.enums.ExceptionEnum;
import com.cemp.modyo.pokemon.exception.BadRequestException;
import com.cemp.modyo.pokemon.exception.DataException;
import org.springframework.util.Assert;

public class ValidatorUtil {

    private static String MESSAGE = "";
    
    private ValidatorUtil() {
        // This is intentional
    }

    public static void validateParams(Integer offset, Integer limit) {
        try {
            Assert.isTrue(offset != null ^ limit == null, "");
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(ExceptionEnum.INVALID_PARAMS);
        }
    }

    public static void validatePoke(Poke o) {
        String msg = "Excepción ocurrió con la lista de Pokemons. ";
        try {
            Assert.notNull(o, "No se pudo encontrar una lista de pokemons.");
            Assert.notNull(o.getCount(), "Dato 'count' es inválido.");
            Assert.notNull(o.getResults(), "Lista de pokemons es inválida.");
            Assert.notEmpty(o.getResults(), "Lista de pokemons esta vacia.");
            Assert.noNullElements(o.getResults(), 
                    "Uno o más datos de los pokemons es inválido.");

        } catch (IllegalArgumentException e) {
            throw new DataException(ExceptionEnum.INVALID_DATA, msg + e.getMessage());
        }
    }

    public static void validatePokeDetail(PokeDetail o) {
        String msg = "Excepción ocurrió con los detalles del pokemon. ";
        try {
            Assert.notNull(o, "No se pudo encontrar el detalle del pokemon.");
            Assert.notNull(o.getName(), "Nombre del pokemon es inválido.");
            Assert.notNull(o.getWeight(), "Peso del pokemon es inválido.");

            Assert.notNull(o.getSprites(), "Descripción (sprites) del pokemon es inválida.");
            Assert.notNull(o.getSprites().getFrontDefault(), "Foto del pokemopn es inválida.");
            Assert.hasText(o.getSprites().getFrontDefault(), "Foto del pokemopn es inválida.");

            Assert.notNull(o.getAbilities(), "Datos de las habilidades del pokemon son inválidos.");
            Assert.notEmpty(o.getAbilities(), "No se pudieron encontrar las habilidades del pokemon.");
            Assert.noNullElements(o.getAbilities(), 
                    "Uno o más datos de las habilidades del pokemon son inválidos.");

            Assert.notNull(o.getTypes(), "No se pudo encontrar los datos del tipo del pokemon.");
            Assert.notEmpty(o.getTypes(), "No existen datos del tipo de pokemon");
            Assert.noNullElements(o.getTypes(), "Uno o más datos de tipo de pokemon son inválidos.");

            Assert.notNull(o.getSpecies(), "No se pudo encontrar la especie del pokemon.");
            Assert.notNull(o.getSpecies().getName(), "Nombre de la especie del pokemon es inválido.");
            Assert.hasText(o.getSpecies().getName(), "Nombre de la especie del pokemon es inválido.");

        } catch (IllegalArgumentException e) {
            throw new DataException(ExceptionEnum.INVALID_DATA, msg + e.getMessage());
        }
    }

    public static void validatePokeSpecies(PokeSpecies o) {
        String msg = "Excepción ocurrió con los detalles de la especie del pokemon. ";
        try {
            Assert.notNull(o, "No se pudo encontrar la especie del pokemon.");

            Assert.notNull(o.getEvolvesFromSpecies(), 
                    "Datos de la evolución del pokemon son inválidos.");
            Assert.notNull(o.getEvolvesFromSpecies().getName(), 
                    "Especie de evolución del pokemon es inválida.");
            Assert.hasText(o.getEvolvesFromSpecies().getName(),
                    "Especie de evolución del pokemon es inválida.");

            Assert.notNull(o.getColor(), "Datos del color del pokemon son inválidos.");
            Assert.notNull(o.getColor().getName(), "Color del pokemon es inválido.");
            Assert.hasText(o.getColor().getName(), "Color del pokemon es inválido.");

            Assert.notNull(o.getGeneration(), 
                    "Datos de la generación del pokemon son inválidos.");
            Assert.notNull(o.getGeneration().getName(), "Generación del pokemon es inválida.");
            Assert.hasText(o.getGeneration().getName(), "Generación del pokemon es inválida.");

            Assert.notNull(o.getHabitat(), "Datos del habitat del pokemon son inválidos.");
            Assert.notNull(o.getHabitat().getName(), "Habitat del pokemon es inválido.");
            Assert.hasText(o.getHabitat().getName(), "Habitat del pokemon es inválido.");

            Assert.notNull(o.getShape(), "Datos de la forma del pokemon son inválidos.");
            Assert.notNull(o.getShape().getName(), "Forma del pokemon es inválida.");
            Assert.hasText(o.getShape().getName(), "Forma del pokemon es inválida.");

            Assert.notNull(o.getEvolutionChain(), 
                    "Los datos de la cadena de evolución del pokemon son inválidos.");
            Assert.notNull(o.getEvolutionChain().getUrl(), 
                    "Dato de la cadena de evolución del pokemon es inválido.");
            Assert.hasText(o.getEvolutionChain().getUrl(),
                    "Dato de la cadena de evolución del pokemon es inválido.");

            Assert.notNull(o.getGenera(), "Datos del genera del pokemon son inválidos.");
            Assert.notEmpty(o.getGenera(), "No se encuentran datos de genera del pokemon.");
            Assert.noNullElements(o.getGenera(), 
                    "Uno o más datos del genera del pokemon son inválidos.");

            Assert.notNull(o.getDescriptions(), "Datos de la descripcion del pokemon son inválidos.");
            Assert.notEmpty(o.getDescriptions(), "No se encuentran datos de la descripcion del pokemon.");
            Assert.noNullElements(o.getDescriptions(), 
                    "Uno o más datos de la descripción del pokemon es inválido.");

        } catch (IllegalArgumentException e) {
            throw new DataException(ExceptionEnum.INVALID_DATA, msg + e.getMessage());
        }
    }

    public static void validatePokeEvolution(PokeEvolution o) {
        String msg = "Excepción ocurrió con los detalles de la evolución del pokemon. ";
        try {
            Assert.notNull(o, "Datos de la cadena de evolución del pokemon son inválidos.");
            Assert.notNull(o.getChain(), "Cadena de evolución del pokemon es inválida.");

        } catch (IllegalArgumentException e) {

            throw new DataException(ExceptionEnum.INVALID_DATA, msg + e.getMessage());
        }
    }

}
