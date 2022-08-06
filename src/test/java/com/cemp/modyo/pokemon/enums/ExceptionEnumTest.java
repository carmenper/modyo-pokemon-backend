package com.cemp.modyo.pokemon.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExceptionEnumTest {

    @Test
    void test() {
        ExceptionEnum e1 = ExceptionEnum.INVALID_ID;
        ExceptionEnum e2 = ExceptionEnum.NOT_FOUND;
        ExceptionEnum e3 = ExceptionEnum.COMMUNICATION_EXCEPTION;
        ExceptionEnum e4 = ExceptionEnum.INVALID_DATA;
        ExceptionEnum e5 = ExceptionEnum.INVALID_PARAMS;
        e1.getDetail();
        e1.getId();
        Assertions.assertNotNull(e1);
    }
}
