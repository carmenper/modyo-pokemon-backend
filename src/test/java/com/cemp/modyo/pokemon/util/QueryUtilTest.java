package com.cemp.modyo.pokemon.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

class QueryUtilTest {

    @Test
    void getQueryMap_offsetAndLimit_Test() throws MalformedURLException {

        URL url = new URL("https://pokeapi.co/api/v2/pokemon?offset=200&limit=100");

        Map<String, Integer> map = QueryUtil.getQueryMap(url);

        Assertions.assertNotNull(map);
        Assertions.assertEquals(2, map.size());
        Assertions.assertTrue(map.containsKey("offset"));
        Assertions.assertTrue(map.containsKey("limit"));
        
    }

    @Test
    void getQueryMap_offsetOnlyParam_Test() throws MalformedURLException {

        URL url = new URL("https://pokeapi.co/api/v2/pokemon?offset=200");

        Map<String, Integer> map = QueryUtil.getQueryMap(url);

        Assertions.assertNotNull(map);
        Assertions.assertEquals(1, map.size());
        Assertions.assertTrue(map.containsKey("offset"));

    }

    @Test
    void getQueryMap_limitOnlyParam_Test() throws MalformedURLException {

        URL url = new URL("https://pokeapi.co/api/v2/pokemon?limit=1000");

        Map<String, Integer> map = QueryUtil.getQueryMap(url);

        Assertions.assertNotNull(map);
        Assertions.assertEquals(1, map.size());
        Assertions.assertTrue(map.containsKey("limit"));

    }


    @Test
    void getQueryMap_noQueryParams_Test() throws MalformedURLException {

        URL url = new URL("https://pokeapi.co/api/v2/pokemon");

        Map<String, Integer> map = QueryUtil.getQueryMap(url);

        Assertions.assertNotNull(map);
        Assertions.assertEquals(0, map.size());

    }
}
