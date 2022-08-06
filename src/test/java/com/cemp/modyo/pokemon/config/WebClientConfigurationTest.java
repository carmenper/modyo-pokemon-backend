package com.cemp.modyo.pokemon.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WebClientConfiguration.class })
class WebClientConfigurationTest {

    @Autowired
    ApplicationContext context;

    @Test
    void HttpClientTest() {
        Assertions.assertTrue(context.containsBean("httpClient"));
        Assertions.assertNotNull(context.getBean(HttpClient.class));
    }

    @Test
    void WebClientTest() {
        Assertions.assertTrue(context.containsBean("webClient"));
        Assertions.assertNotNull(context.getBean(WebClient.class));
    }
}
