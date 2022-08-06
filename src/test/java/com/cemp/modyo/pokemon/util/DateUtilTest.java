package com.cemp.modyo.pokemon.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DateUtilTest {


    @Test
    void getTimeStampTest() {

        String timeStamp = DateUtil.getTimeStamp(LocalDateTime.now());
        Assertions.assertNotNull(timeStamp);
    }

    @Test
    void getTimeStampVoidTest() {
        Assertions.assertNotNull(DateUtil.getTimeStamp());
    }

}
