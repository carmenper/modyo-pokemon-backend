package com.cemp.modyo.pokemon.util;

import com.cemp.modyo.pokemon.dto.*;
import com.cemp.modyo.pokemon.enums.ExceptionEnum;
import com.cemp.modyo.pokemon.exception.BadRequestException;
import com.cemp.modyo.pokemon.exception.DataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ValidatorUtilTest {

    @Test
    void validateParamsTest() {

        ValidatorUtil.validateParams(null, null);
        ValidatorUtil.validateParams("20", "20");

        try {
            ValidatorUtil.validateParams("20", null);
        } catch (BadRequestException e) {
            Assertions.assertEquals(ExceptionEnum.INVALID_PARAMS.getDetail(), e.getMessage());
        }

        try {
            ValidatorUtil.validateParams(null, "20");
        } catch (BadRequestException e) {
            Assertions.assertEquals(ExceptionEnum.INVALID_PARAMS.getDetail(), e.getMessage());
        }

    }

    @Test
    void validatePoke() {
        String msg = "Excepción ocurrió con la lista de Pokemons. ";
        try {
            ValidatorUtil.validatePoke(null);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "No se pudo encontrar una lista de pokemons.", e.getData());
        }
        Poke p = new Poke();
        try {
            ValidatorUtil.validatePoke(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Dato 'count' es inválido.", e.getData());
        }
        p.setCount(1);
        try {
            ValidatorUtil.validatePoke(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Lista de pokemons es inválida.", e.getData());
        }
        List<PokeNameUrl> pokeNameUrls = new ArrayList<>();
        p.setResults(pokeNameUrls);
        try {
            ValidatorUtil.validatePoke(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Lista de pokemons esta vacia.", e.getData());
        }

        PokeNameUrl pokeNameUrl = new PokeNameUrl();
        pokeNameUrl.setName("name");
        pokeNameUrl.setUrl("url");
        pokeNameUrls.add(pokeNameUrl);

        ValidatorUtil.validatePoke(p);

        pokeNameUrls.add(null);
        try {
            ValidatorUtil.validatePoke(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Uno o más datos de los pokemons es inválido.",
                    e.getData());
        }
    }

    @Test
    void validatePokeDetailTest() {
        String msg = "Excepción ocurrió con los detalles del pokemon. ";
        try {
            ValidatorUtil.validatePokeDetail(null);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "No se pudo encontrar el detalle del pokemon.", e.getData());
        }
        PokeDetail p = new PokeDetail();
        try {
            ValidatorUtil.validatePokeDetail(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Nombre del pokemon es inválido.", e.getData());
        }
        p.setName("name");
        try {
            ValidatorUtil.validatePokeDetail(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Peso del pokemon es inválido.", e.getData());
        }
        p.setWeight(1);
        try {
            ValidatorUtil.validatePokeDetail(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Descripción (sprites) del pokemon es inválida.", e.getData());
        }
        PokeSprites ps = new PokeSprites();
        p.setSprites(ps);
        try {
            ValidatorUtil.validatePokeDetail(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Foto del pokemopn es inválida.", e.getData());
        }
        ps.setFrontDefault("");
        try {
            ValidatorUtil.validatePokeDetail(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Foto del pokemopn es inválida.", e.getData());
        }
        ps.setFrontDefault("foto");
        try {
            ValidatorUtil.validatePokeDetail(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Datos de las habilidades del pokemon son inválidos.",
                    e.getData());
        }
        List<PokeAbility> pas = new ArrayList<>();
        p.setAbilities(pas);
        try {
            ValidatorUtil.validatePokeDetail(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "No se pudieron encontrar las habilidades del pokemon.",
                    e.getData());
        }
        pas.add(null);
        try {
            ValidatorUtil.validatePokeDetail(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Uno o más datos de las habilidades del pokemon son inválidos.",
                    e.getData());
        }
        pas.clear();
        pas.add(new PokeAbility());
        try {
            ValidatorUtil.validatePokeDetail(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "No se pudo encontrar los datos del tipo del pokemon.",
                    e.getData());
        }
        List<PokeType> pts = new ArrayList<>();
        p.setTypes(pts);
        try {
            ValidatorUtil.validatePokeDetail(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "No existen datos del tipo de pokemon",
                    e.getData());
        }
        pts.add(null);
        try {
            ValidatorUtil.validatePokeDetail(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Uno o más datos de tipo de pokemon son inválidos.",
                    e.getData());
        }
        pts.clear();
        pts.add(new PokeType());
        try {
            ValidatorUtil.validatePokeDetail(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "No se pudo encontrar la especie del pokemon.",
                    e.getData());
        }
        PokeNameUrl pokeNameUrle = new PokeNameUrl();
        p.setSpecies(pokeNameUrle);
        try {
            ValidatorUtil.validatePokeDetail(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Nombre de la especie del pokemon es inválido.",
                    e.getData());
        }
        pokeNameUrle.setName("");
        try {
            ValidatorUtil.validatePokeDetail(p);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Nombre de la especie del pokemon es inválido.",
                    e.getData());
        }
        pokeNameUrle.setName("species");
        ValidatorUtil.validatePokeDetail(p);
    }

    @Test
    void validatePokeSpeciesTest() {
        String msg = "Excepción ocurrió con los detalles de la especie del pokemon. ";
        try {
            ValidatorUtil.validatePokeSpecies(null);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "No se pudo encontrar la especie del pokemon.",
                    e.getData());
        }
        PokeSpecies pe = new PokeSpecies();
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Datos del color del pokemon son inválidos.",
                    e.getData());
        }
        PokeNameUrl pec = new PokeNameUrl();
        pe.setColor(pec);
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Color del pokemon es inválido.",
                    e.getData());
        }
        pec.setName("");
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Color del pokemon es inválido.",
                    e.getData());
        }
        pec.setName("color");
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Datos de la generación del pokemon son inválidos.",
                    e.getData());
        }
        PokeNameUrl peg = new PokeNameUrl();
        pe.setGeneration(peg);
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Generación del pokemon es inválida.",
                    e.getData());
        }
        peg.setName("");
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Generación del pokemon es inválida.",
                    e.getData());
        }
        peg.setName("generation");


        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Datos del habitat del pokemon son inválidos.",
                    e.getData());
        }
        PokeNameUrl peh = new PokeNameUrl();
        pe.setHabitat(peh);
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Habitat del pokemon es inválido.",
                    e.getData());
        }
        peh.setName("");
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Habitat del pokemon es inválido.",
                    e.getData());
        }
        peh.setName("habitat");


        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Datos de la forma del pokemon son inválidos.",
                    e.getData());
        }
        PokeNameUrl pesh = new PokeNameUrl();
        pe.setShape(pesh);
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Forma del pokemon es inválida.",
                    e.getData());
        }
        pesh.setName("");
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Forma del pokemon es inválida.",
                    e.getData());
        }
        pesh.setName("shape");
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Los datos de la cadena de evolución del pokemon son inválidos.",
                    e.getData());
        }
        PokeNameUrl peec = new PokeNameUrl();
        pe.setEvolutionChain(peec);
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Dato de la cadena de evolución del pokemon es inválido.",
                    e.getData());
        }
        peec.setUrl("");
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Dato de la cadena de evolución del pokemon es inválido.",
                    e.getData());
        }
        peec.setUrl("url");
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Datos del genera del pokemon son inválidos.",
                    e.getData());
        }
        List<PokeGenus> peGenus = new ArrayList<>();
        pe.setGenera(peGenus);
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "No se encuentran datos de genera del pokemon.",
                    e.getData());
        }
        peGenus.add(null);
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Uno o más datos del genera del pokemon son inválidos.",
                    e.getData());
        }
        peGenus.clear();
        peGenus.add(new PokeGenus());
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Datos de la descripcion del pokemon son inválidos.",
                    e.getData());
        }
        List<PokeDescription> ped = new ArrayList<>();
        pe.setDescriptions(ped);
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "No se encuentran datos de la descripcion del pokemon.",
                    e.getData());
        }
        ped.add(null);
        try {
            ValidatorUtil.validatePokeSpecies(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Uno o más datos de la descripción del pokemon es inválido.",
                    e.getData());
        }
        ped.clear();
        ped.add(new PokeDescription());
        ValidatorUtil.validatePokeSpecies(pe);
    }

    @Test
    void validatePokeEvolutionTest() {
        String msg = "Excepción ocurrió con los detalles de la evolución del pokemon. ";
        try {
            ValidatorUtil.validatePokeEvolution(null);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Datos de la cadena de evolución del pokemon son inválidos.",
                    e.getData());
        }
        PokeEvolution pe = new PokeEvolution();
        try {
            ValidatorUtil.validatePokeEvolution(pe);
        } catch (DataException e) {
            Assertions.assertEquals(msg + "Cadena de evolución del pokemon es inválida.",
                    e.getData());
        }
        pe.setChain(new PokeChain());
        ValidatorUtil.validatePokeEvolution(pe);
    }
}
