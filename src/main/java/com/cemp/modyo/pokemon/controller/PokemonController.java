package com.cemp.modyo.pokemon.controller;

import com.cemp.modyo.pokemon.enums.ExceptionEnum;
import com.cemp.modyo.pokemon.exception.ApplicationException;
import com.cemp.modyo.pokemon.generic.CustomOptional;
import com.cemp.modyo.pokemon.service.PokemonService;
import com.cemp.modyo.pokemon.util.StringUtil;
import com.cemp.modyo.pokemon.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(value = "/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPokemons(@RequestParam(name = "offset", required = false) String offsetString,
                                              @RequestParam(name = "limit", required = false) String limitString) {

        ValidatorUtil.validateParams(CustomOptional.ofNullable(offsetString).orElseNull(),
                CustomOptional.ofNullable(limitString).orElseNull());

        Integer offset = StringUtil.getStringToIntegerValue(CustomOptional.ofNullable(offsetString).orElseNull());
        Integer limit = StringUtil.getStringToIntegerValue(CustomOptional.ofNullable(limitString).orElseNull());

        return ResponseEntity
                .ok(pokemonService.getPokemons(CustomOptional.ofNullable(offset).orElseNull(),
                        CustomOptional.ofNullable(limit).orElseNull()));
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPokemon(@PathVariable("id") String id) {

        if (id == null || id.isBlank()) {
            throw new ApplicationException(ExceptionEnum.INVALID_ID);
        }

        return ResponseEntity
                .ok(pokemonService.getPokemon(id));
    }

}
