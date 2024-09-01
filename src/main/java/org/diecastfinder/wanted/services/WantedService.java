package org.diecastfinder.wanted.services;

import java.io.File;
import java.util.List;
import org.diecastfinder.model.FoundModelDto;
import org.diecastfinder.model.WantedModelDto;

import java.util.UUID;

public interface WantedService {
    WantedModelDto getWantedModelById(UUID id);

    WantedModelDto saveNewModel(WantedModelDto wanted);

    WantedModelDto updateModel(UUID id, WantedModelDto wanted);

    void deleteModel(UUID id);

    File saveModelsAsCsv(List<FoundModelDto> foundModelDtoList);
}
