package org.diecastfinder.wanted.pact;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.LambdaDsl;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.MockServerConfig;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.diecastfinder.model.WantedModelDto;
import org.diecastfinder.model.WantedModelDtoFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "Crawler", pactVersion = PactSpecVersion.V4)
@MockServerConfig(hostInterface = "localhost", port = "1234")
public class WantedConsumerPactTest {

    public static final String ENDPOINT = "/api/v1/crawler/find";
    public static final WantedModelDto nonexistedModelDto = WantedModelDtoFactory.getWantedModelDto().setName("kfhli-never-found-ihgiu");

    @SneakyThrows
    @Pact(consumer = "Wanted", provider = "Crawler")
    public V4Pact getFoundModel(PactDslWithProvider builder) {
        return builder
            .given("Query returns few models")
            .uponReceiving("Found Models")
            .path(ENDPOINT)
            .method("POST")
            .headers(headers())
            .body(WantedModelDtoFactory.getWantedModelDto().asJsonString())
            .willRespondWith()
            .status(200)
            .headers(headers())
            .body(LambdaDsl.newJsonArrayMinLike(1, array ->
                    array.object(obj ->
                        obj.stringType("uri", "https://allegrolokalnie.pl/oferta")))
                .build())
            .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "getFoundModel")
    public void getFoundModel_whenWantedModelFound(MockServer mockServer) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");

        HttpEntity<WantedModelDto> request = new HttpEntity<>(WantedModelDtoFactory.getWantedModelDto(), httpHeaders);
        ResponseEntity<List<Object>> response = new RestTemplate().exchange(mockServer.getUrl() + ENDPOINT,
            HttpMethod.POST, request, new ParameterizedTypeReference<List<Object>>() {});

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();
    }

    @SneakyThrows
    @Pact(consumer = "Wanted", provider = "Crawler")
    public V4Pact getNoModels(PactDslWithProvider builder) {

        return builder
            .given("Query returns no models")
            .uponReceiving("Not found Models")
            .path(ENDPOINT)
            .method("POST")
            .headers(headers())
            .body(nonexistedModelDto.asJsonString())
            .willRespondWith()
            .status(200)
            .headers(headers())
            .body(LambdaDsl.newJsonArray(array -> {})
                .build())
            .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "getNoModels")
    public void getFoundModel_whenWantedModelNotFound(MockServer mockServer) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");

        HttpEntity<WantedModelDto> request = new HttpEntity<>(nonexistedModelDto, httpHeaders);
        ResponseEntity<List<Object>> response = new RestTemplate().exchange(mockServer.getUrl() + ENDPOINT,
            HttpMethod.POST, request, new ParameterizedTypeReference<List<Object>>() {});

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();
    }

    private Map<String, String> headers() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
