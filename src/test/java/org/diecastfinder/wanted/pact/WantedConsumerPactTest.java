package org.diecastfinder.wanted.pact;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.MockServerConfig;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.diecastfinder.model.FoundModelDto;
import org.diecastfinder.model.FoundModelDtoFactory;
import org.diecastfinder.model.WantedModelDto;
import org.diecastfinder.model.WantedModelDtoFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @SneakyThrows
    @Pact(consumer = "Wanted", provider = "Crawler")
    public V4Pact getFoundModel(PactDslWithProvider builder) {
        return builder
            .given("Query returns few models")
                .uponReceiving("Found Models")
                .path(ENDPOINT)
                .method("POST")
                .headers(headers())
                .body(WantedModelDtoFactory.getWantedModelDtoAsString())
            .willRespondWith()
                .status(200)
                .headers(headers())
                .body(FoundModelDtoFactory.getFoundModelDtoAsString())
            .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "getFoundModel")
    public void getFoundModel_whenWantedModelFound(MockServer mockServer) throws IOException {
        FoundModelDto expected = FoundModelDtoFactory.getFoundModelDto();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json; charset=utf-8");

        HttpEntity<WantedModelDto> request = new HttpEntity<>(WantedModelDtoFactory.getWantedModelDto(), httpHeaders);
        ResponseEntity<FoundModelDto> response = new RestTemplate().exchange(mockServer.getUrl() + ENDPOINT, HttpMethod.POST, request, FoundModelDto.class);

        assertThat(response.getStatusCode().value() == 200);
        assertEquals(response.getBody(), expected);
    }

    private Map<String, String> headers() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }
}
