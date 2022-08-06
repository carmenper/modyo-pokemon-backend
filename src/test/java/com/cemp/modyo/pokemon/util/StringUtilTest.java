package com.cemp.modyo.pokemon.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringUtilTest {

    @Test
    void getValue() {
        Assertions.assertNull(StringUtil.getStringToIntegerValue(null));
        Assertions.assertNull(StringUtil.getStringToIntegerValue("a"));
        Assertions.assertNotNull(StringUtil.getStringToIntegerValue("1"));
    }
}
