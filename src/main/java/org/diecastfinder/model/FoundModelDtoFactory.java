package org.diecastfinder.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.SneakyThrows;

public class FoundModelDtoFactory {

    public static FoundModelDto getFoundModelDto() {
        return FoundModelDto.builder()
            .nameRequested("McLaren MP4")
            .producer("minichamps")
            .price(800)
            .currency("PLN")
            .lotTitle("McLaren Honda MP4-30 No.22 Australian GP 2015")
            .uri("https://allegrolokalnie.pl/oferta/mclaren-honda-mp430-no22-australian-gp-2015?navCategoryId=")
            .build();
    }
}
