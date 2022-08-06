package com.cemp.modyo.pokemon.util;

import com.cemp.modyo.pokemon.dto.PokeMarker;
import lombok.Data;

import java.net.MalformedURLException;
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

    public static PokeMarker getPokeMarker(String url) {
        if (url != null) {
            try {
                Map<String, Integer> map = QueryUtil.getQueryMap(new URL(url));
                return new PokeMarker(PaginationUtil.getOffset(map),
                        PaginationUtil.getLimit(map));
            } catch (MalformedURLException e) {
                return null;
            }
        }
        return null;
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
                        this.value = StringUtil.getStringToIntegerValue(stringSplit[1]);
                    }
                }
            }
        }
    }
}
