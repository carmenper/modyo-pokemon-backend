package com.cemp.modyo.pokemon.exception;

import com.cemp.modyo.pokemon.enums.ExceptionEnum;

public class NotFoundException extends ApplicationException {

    public NotFoundException() {
        super(ExceptionEnum.NOT_FOUND);
    }

}
