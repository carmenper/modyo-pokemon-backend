package com.cemp.modyo.pokemon.dto;

import lombok.Data;

@Data
public class PokeMarker {

    public PokeMarker(Integer offset, Integer limit) {
        this.offset = offset;
        this.limit = limit;
    }

    private Integer offset;
    private Integer limit;

}
