package com.cemp.modyo.pokemon.util;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

class QueryUtilTest {

    @Test
    void getQueryMapTest() throws MalformedURLException {

        //URL url = new URL("https://pokeapi.co/api/v2/pokemon?offset=200&limit=1000");

        URL url = new URL("https://pokeapi.co/api/v2/pokemon");

        Map<String, Integer> map = QueryUtil.getQueryMap(url);

        System.out.println(map.keySet());
        
    }
}
