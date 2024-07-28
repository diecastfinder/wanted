package org.diecastfinder.wanted.services;

import org.diecastfinder.wanted.web.model.WantedModelDto;

import java.util.UUID;

public interface WantedService {
    WantedModelDto getWantedModelById(UUID id);

    WantedModelDto saveNewModel(WantedModelDto wanted);

    WantedModelDto updateModel(UUID id, WantedModelDto wanted);

    void deleteModel(UUID id);
}
