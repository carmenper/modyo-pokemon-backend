package com.cemp.modyo.pokemon.util;

import java.util.Map;

public class PaginationUtil {

    private static final String OFFSET = "offset";
    private static final String LIMIT = "limit";

    private PaginationUtil() {}

    public static Integer getOffset(Map<String, Integer> map) {
        if (map != null && map.containsKey(OFFSET)) {
            return map.get(OFFSET);
        }
        return null;
    }

    public static Integer getLimit(Map<String, Integer> map) {
        if (map != null && map.containsKey(LIMIT)) {
            return map.get(LIMIT);
        }
        return null;
    }
}
