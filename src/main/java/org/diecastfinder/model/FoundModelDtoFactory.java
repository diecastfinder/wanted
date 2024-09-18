package org.diecastfinder.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class FoundModelDtoFactory {

    public static FoundModelDto getFoundModelDto() {
        return FoundModelDto.builder()
            .nameRequested("Mercedes W196R stromliner")
            .producer("CMC")
            .price(1500)
            .currency("PLN")
            .lotTitle("Mercedes W196R 1955-1956")
            .uri("some_url")
            .build();
    }

    @SneakyThrows
    public static String getFoundModelDtoAsString() {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(getFoundModelDto());
    }
}
