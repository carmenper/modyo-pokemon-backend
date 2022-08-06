package com.cemp.modyo.pokemon.exception;

import com.cemp.modyo.pokemon.enums.ExceptionEnum;

public class DataException extends ApplicationException {

    private final String data;

    public DataException(ExceptionEnum exceptionEnum, String data) {
        super(exceptionEnum);
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
