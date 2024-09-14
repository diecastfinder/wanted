package org.diecastfinder.wanted.services.crawler;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.diecastfinder.model.FoundModelDto;
import org.diecastfinder.model.WantedModelDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@ConfigurationProperties(prefix = "diecastfinder", ignoreUnknownFields = true)
@Component
public class CrawlerServiceRestTemplate implements CrawlerService {

    public static final String CRAWLER_PATH = "/api/v1/crawler/find";
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;


    private String crawlerServiceHost;

    public void setCrawlerServiceHost(String crawlerServiceHost) {
        this.crawlerServiceHost = crawlerServiceHost;
    }

    public CrawlerServiceRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public List<FoundModelDto> findModel(WantedModelDto wanted) {

        log.debug("Calling Crawler Service");

        HttpEntity<WantedModelDto> request = new HttpEntity<>(wanted, headers);

        ResponseEntity<List<FoundModelDto>> response = restTemplate
            .exchange(crawlerServiceHost + CRAWLER_PATH, HttpMethod.POST, request,
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }
}
