package com.cemp.modyo.pokemon.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfiguration {

    @Value("${poke-api.url:https://pokeapi.co/api/v2}")
    private String baseUrl;

    @Value("${poke-api.timeout.connect:5000}")
    private String connectTimeout;

    @Value("${poke-api.timeout.response:5000}")
    private String responseTimeout;

    @Value("${poke-api.timeout.read:5000}")
    private String readTimeout;

    @Value("${poke-api.timeout.write:5000}")
    private String writeTimeout;

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.parseInt(connectTimeout))
                .responseTimeout(Duration.ofMillis(Integer.parseInt(responseTimeout)))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(Integer.parseInt(readTimeout),
                                        TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(Integer.parseInt(writeTimeout),
                                        TimeUnit.MILLISECONDS)));
    }

    @Bean
    public WebClient webClient(HttpClient httpClient) {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        return WebClient.builder()
                .baseUrl(baseUrl)
                .exchangeStrategies(strategies)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
