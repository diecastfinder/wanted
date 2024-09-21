package org.diecastfinder.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class WantedModelDtoFactory {
    
    public static WantedModelDto getWantedModelDto() {
        return WantedModelDto.builder()
            .name("McLaren MP4")
            .producer("minichamps")
            .maxPrice(2000)
            .minPrice(400)
            .currency("PLN")
            .active(true)
            .build();
    }
}

