package org.diecastfinder.wanted.services;

import org.diecastfinder.wanted.web.model.WantedModelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class WantedServiceImpl implements WantedService {

    @Override
    public WantedModelDto getWantedModelById(UUID id) {
        log.debug("Showing a model...");
        return WantedModelDto.builder()
                .id(id)
                .name("McLaren P1")
                .producer("Autoart")
                .minPrice(350)
                .maxPrice(800)
                .currency("PLN")
                .build();
    }

    @Override
    public WantedModelDto saveNewModel(WantedModelDto wanted) {
        log.debug("Saving a model...");
        return WantedModelDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public WantedModelDto updateModel(UUID id, WantedModelDto wanted) {
        log.debug("Updating a model...");
        return WantedModelDto.builder()
                .id(id)
                .name(wanted.getName())
                .producer(wanted.getProducer())
                .minPrice(wanted.getMinPrice())
                .maxPrice(wanted.getMaxPrice())
                .currency(wanted.getCurrency())
                .build();
    }

    @Override
    public void deleteModel(UUID id) {
        // todo delete model in persistence layer in future
        log.debug("Deleting a model...");
    }
}
