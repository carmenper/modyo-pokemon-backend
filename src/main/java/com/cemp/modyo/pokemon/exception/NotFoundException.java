package com.cemp.modyo.pokemon.exception;

import com.cemp.modyo.pokemon.enums.ExceptionEnum;

public class NotFoundException extends ApplicationException {

    private final String resourceId;

    public NotFoundException(String resourceId) {
        super(ExceptionEnum.NOT_FOUND);
        this.resourceId = resourceId;
    }

        public String getResourceId() {
        return resourceId;
    }
}
