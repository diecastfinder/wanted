package gns.diecastfinder.wanted.services;

import gns.diecastfinder.wanted.web.model.WantedModelDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class WantedServiceImpl implements WantedService {

    @Override
    public WantedModelDto getWantedModelById(UUID id) {
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
        return WantedModelDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public WantedModelDto updateModel(UUID id, WantedModelDto wanted) {
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
