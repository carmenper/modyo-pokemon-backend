package com.cemp.modyo.pokemon.controller;

import com.cemp.modyo.pokemon.enums.ExceptionEnum;
import com.cemp.modyo.pokemon.exception.ApplicationException;
import com.cemp.modyo.pokemon.pojo.CustomOptional;
import com.cemp.modyo.pokemon.service.PokemonService;
import com.cemp.modyo.pokemon.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPokemons(@RequestParam(name = "offset", required = false) Integer offset,
                                              @RequestParam(name = "limit", required = false) Integer limit) {

        ValidatorUtil.validateParams(CustomOptional.ofNullable(offset).orElseNull(),
                CustomOptional.ofNullable(limit).orElseNull());

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
