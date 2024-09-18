package org.diecastfinder.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class WantedModelDtoFactory {
    
    public static WantedModelDto getWantedModelDto() {
        return WantedModelDto.builder()
            .name("Mercedes W196R stromliner")
            .producer("CMC")
            .maxPrice(2000)
            .minPrice(400)
            .currency("PLN")
            .active(true)
            .build();
    }

    @SneakyThrows
    public static String getWantedModelDtoAsString() {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(getWantedModelDto());
    }
}

