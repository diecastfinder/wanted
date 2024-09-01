package org.diecastfinder.wanted.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang.StringUtils;
import org.diecastfinder.model.FoundModelDto;
import org.diecastfinder.model.WantedModelDto;
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
        //todo: delete model in persistence layer in future
        log.debug("Deleting a model...");
    }

    @Override
    public File saveModelsAsCsv(List<FoundModelDto> foundModelDtoList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String fileName = "models_" + timestamp + ".csv";

        File file = new File(fileName);

        try (FileWriter writer = new FileWriter(fileName)) {
            // Write CSV header
            writer.append("Query,Name,Producer,Price,Currency,Link\n");

            // Write book data
            for (FoundModelDto model : foundModelDtoList) {
                writer.append(StringUtils.join(List
                    .of(Objects.requireNonNullElse(model.getNameRequested(), ""),
                        Objects.requireNonNullElse(model.getLotTitle(), ""),
                        Objects.requireNonNullElse(model.getProducer(), ""),
                        Objects.requireNonNullElse(model.getPrice(), ""),
                        Objects.requireNonNullElse(model.getCurrency(), ""),
                        Objects.requireNonNullElse(model.getUri(), "")), ","))
                    .append("\n");
            }

            log.info("Models saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
