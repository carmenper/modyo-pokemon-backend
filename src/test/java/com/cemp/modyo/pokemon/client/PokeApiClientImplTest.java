package com.cemp.modyo.pokemon.client;

import com.cemp.modyo.pokemon.client.impl.PokeApiClientImpl;
import com.cemp.modyo.pokemon.dto.Poke;
import com.cemp.modyo.pokemon.dto.PokeDetail;
import com.cemp.modyo.pokemon.dto.PokeEvolution;
import com.cemp.modyo.pokemon.dto.PokeSpecies;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.test.StepVerifier;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@ExtendWith(MockitoExtension.class)
class PokeApiClientImplTest {

    public static MockWebServer mockBackEnd;

    @InjectMocks
    private PokeApiClientImpl pokeApiClient;

    @Spy
    private WebClient webClient;

    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s",
                mockBackEnd.getPort());

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000,
                                        TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000,
                                        TimeUnit.MILLISECONDS)));

        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        WebClient wc =  WebClient.builder()
                .baseUrl(baseUrl)
                .exchangeStrategies(strategies)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        ReflectionTestUtils.setField(pokeApiClient, "webClient", wc);

    }

    @Test
    void retrievePokeTest() throws IOException {
        Poke pokeMocked = mapper
                .readValue(new File("src/test/resources/json/pokes.json"),
                        Poke.class);
        mockBackEnd.enqueue(new MockResponse()
                .setBody(mapper.writeValueAsString(pokeMocked))
                .addHeader("Content-Type", "application/json"));

        Mono<Poke> pokeMono = pokeApiClient.retrievePoke(0, 0);

        StepVerifier.create(pokeMono)
                .expectNextMatches(poke -> poke.getCount()
                        .equals(1154))
                .verifyComplete();
    }

    @Test
    void retrievePokeDetail() throws IOException {
        PokeDetail pokeDetailMocked = mapper
                .readValue(new File("src/test/resources/json/poke.json"),
                        PokeDetail.class);
        mockBackEnd.enqueue(new MockResponse()
                .setBody(mapper.writeValueAsString(pokeDetailMocked))
                .addHeader("Content-Type", "application/json"));

        Mono<PokeDetail> pokeDetailMono = pokeApiClient.retrievePokeDetail("pikachu");

        StepVerifier.create(pokeDetailMono)
                .expectNextMatches(pokeDetail -> pokeDetail.getName()
                        .equals("pikachu"))
                .verifyComplete();
    }

    @Test
    void retrievePokeSpecies() throws IOException {
        PokeSpecies pokeSpeciesMocked = mapper
                .readValue(new File("src/test/resources/json/poke_species.json"),
                        PokeSpecies.class);
        mockBackEnd.enqueue(new MockResponse()
                .setBody(mapper.writeValueAsString(pokeSpeciesMocked))
                .addHeader("Content-Type", "application/json"));

        Mono<PokeSpecies> pokeSpeciesMono = pokeApiClient.retrievePokeSpecies("pikachu");

        StepVerifier.create(pokeSpeciesMono)
                .expectNextMatches(pokeSpecies -> pokeSpecies.getEvolvesFromSpecies().getName()
                        .equals("pichu"))
                .verifyComplete();
    }

    @Test
    void retrievePokeEvolution() throws IOException {
        PokeEvolution pokeEvolutionMocked = mapper
                .readValue(new File("src/test/resources/json/poke_evolution.json"),
                        PokeEvolution.class);
        mockBackEnd.enqueue(new MockResponse()
                .setBody(mapper.writeValueAsString(pokeEvolutionMocked))
                .addHeader("Content-Type", "application/json"));

        Mono<PokeEvolution> pokeEvolutionMono = pokeApiClient.retrievePokeEvolution("10");

        StepVerifier.create(pokeEvolutionMono)
                .expectNextMatches(pokeEvolution -> pokeEvolution.getChain().getSpecies().getName()
                        .equals("pichu"))
                .verifyComplete();
    }
}
