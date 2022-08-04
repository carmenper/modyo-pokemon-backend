package com.cemp.modyo.pokemon.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ClassUtilTest {


    @Test
    void getTimeStampTest() {

        String timeStamp = ClassUtil.getTimeStamp(LocalDateTime.now());
        Assertions.assertNotNull(timeStamp);
    }

    @Test
    void getTimeStampVoidTest() {
        Assertions.assertNotNull(ClassUtil.getTimeStamp());
    }


    @Test
    void getValue() {
        Assertions.assertNull(ClassUtil.getValue(null));
        Assertions.assertNull(ClassUtil.getValue("a"));
        Assertions.assertNotNull(ClassUtil.getValue("1"));
    }
}
