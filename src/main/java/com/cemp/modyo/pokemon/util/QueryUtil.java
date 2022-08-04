package com.cemp.modyo.pokemon.util;

import lombok.Data;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryUtil {

    private QueryUtil() {
        // This is intentional
    }

    public static Map<String, Integer> getQueryMap(URL url) {
        if (url != null && url.getQuery() != null && !url.getQuery().isBlank()) {
            return getMap(url.getQuery());
        }
        return Collections.emptyMap();
    }

    private static Map<String, Integer> getMap(String string) {
        return Arrays.stream(string.split("&"))
                .map(Pair::new)
                .filter(pair -> pair.value != null)
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.
                        toMap(Pair::getKey, Pair::getValue));
    }

    @Data
    private static class Pair {

        private String key;
        private Integer value;

        public Pair(String string) {
            if (string != null && !string.isBlank() && string.contains("=")) {
                String[] stringSplit = string.split("=");
                if (stringSplit[0] != null && !stringSplit[0].isBlank()) {
                    if (stringSplit[1] != null && !stringSplit[1].isBlank()) {
                        this.key = stringSplit[0];
                        this.value = ClassUtil.getValue(stringSplit[1]);
                    }
                }
            }
        }

    }
}
