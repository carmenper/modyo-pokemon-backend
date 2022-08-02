package com.cemp.modyo.pokemon.exception;

import com.cemp.modyo.pokemon.enums.ExceptionEnum;

public class ApplicationException extends RuntimeException
        implements GlobalException {

    private final ExceptionEnum exceptionEnum;

    public ApplicationException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getDetail());
        this.exceptionEnum = exceptionEnum;
    }

    @Override
    public int getId() {
        return this.exceptionEnum.getId();
    }

    @Override
    public String getDetail() {
        return getMessage();
    }
}
