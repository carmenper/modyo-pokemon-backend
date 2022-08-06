package com.cemp.modyo.pokemon.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class PaginationUtilTest {

    @Test
    void getOffset() {
        Assertions.assertNull(PaginationUtil.getOffset(null));
        Map<String, Integer> map = new HashMap<>();
        map.put("offset", 1);
        Assertions.assertNotNull(PaginationUtil.getOffset(map));
    }

    @Test
    void getLimit() {
        Assertions.assertNull(PaginationUtil.getLimit(null));
        Map<String, Integer> map = new HashMap<>();
        map.put("limit", 1);
        Assertions.assertNotNull(PaginationUtil.getLimit(map));

    }
}
