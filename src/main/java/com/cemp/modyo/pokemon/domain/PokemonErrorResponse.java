package com.cemp.modyo.pokemon.domain;

import com.cemp.modyo.pokemon.util.ClassUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class PokemonErrorResponse {

    @Getter
    private final List<ErrorResponse> error;

    public PokemonErrorResponse(int id, String detail) {
        ErrorResponse er = new ErrorResponse(id, detail, ClassUtil.getTimeStamp());
        error = new ArrayList<>();
        error.add(er);
    }

}
