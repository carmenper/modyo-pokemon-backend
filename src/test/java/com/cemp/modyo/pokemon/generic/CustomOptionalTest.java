package com.cemp.modyo.pokemon.generic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class CustomOptionalTest {

    @Test
    void test() {

        String string = null;

        String result = CustomOptional.ofNullable(string).orElseNull();

        Assertions.assertNull(result);

        String result2 = CustomOptional.ofNullable("string").orElseNull();
        Assertions.assertNotNull(result2);

        CustomOptional<String> customOptional = CustomOptional.of("1");
        String r = customOptional.get();

        Assertions.assertNotNull(r);

        customOptional.equals(CustomOptional.of("1"));

        CustomOptional.empty();

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            CustomOptional<String> s = CustomOptional.ofNullable(null);
            s.get();
        });

    }
}
