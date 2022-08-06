package com.cemp.modyo.pokemon.util;

public class StringUtil {

    private StringUtil() {}

    public static Integer getStringToIntegerValue(String string) {
        if (string != null) {
            try {
                return Integer.parseInt(string);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static String getEvolutionId(String url) {
        return url.replace("https://pokeapi.co/api/v2/evolution-chain/", "")
                .replace("/", "");
    }

}
