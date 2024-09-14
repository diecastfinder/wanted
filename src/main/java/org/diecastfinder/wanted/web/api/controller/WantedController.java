package org.diecastfinder.wanted.web.api.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.diecastfinder.model.FoundModelDto;
import org.diecastfinder.wanted.services.WantedService;
import org.diecastfinder.model.WantedModelDto;
import org.diecastfinder.wanted.services.crawler.CrawlerServiceRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/wanted")
@RestController
@RequiredArgsConstructor
public class WantedController {

    private final WantedService wantedService;
    private final CrawlerServiceRestTemplate crawlerService;

    @GetMapping({"/{id}"})
    public ResponseEntity<WantedModelDto> getWantedModel(@PathVariable UUID id) {
        return new ResponseEntity<>(wantedService.getWantedModelById(id), HttpStatus.OK);
    }

    @GetMapping({"/find"})
    @CircuitBreaker(name = "crawlerCircuitBreaker", fallbackMethod = "getFoundModelsFailover")
    public ResponseEntity<List<FoundModelDto>> getFoundModel(@RequestBody WantedModelDto wanted) {
        return new ResponseEntity<>(crawlerService.findModel(wanted), HttpStatus.OK);
    }

    public ResponseEntity<List<FoundModelDto>> getFoundModelsFailover(Throwable throwable) {
        return new ResponseEntity<>(List.of(FoundModelDto.builder().lotTitle("failover stub").build()), HttpStatus.TEMPORARY_REDIRECT);
    }

    @PostMapping
    public ResponseEntity saveWantedModel(@RequestBody WantedModelDto wanted) {
        WantedModelDto savedModel = wantedService.saveNewModel(wanted);
        HttpHeaders headers = new HttpHeaders();

        // todo update to real host name
        headers.add("Location", "http://localhost:8080/api/v1/wanted" + savedModel.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity updateWantedModel(@PathVariable UUID id, @RequestBody WantedModelDto wanted) {
        wantedService.updateModel(id, wanted);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity deleteWantedModel(@PathVariable UUID id) {
        wantedService.deleteModel(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
