package org.diecastfinder.wanted.web.mappers;

import org.diecastfinder.wanted.domain.WantedModel;
import org.diecastfinder.wanted.web.model.WantedModelDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WantedModelMapper {

    WantedModelDto wantedModelToWantedModelDto(WantedModel wantedModel);

    WantedModel wantedModelDtoToWantedModel(WantedModelDto wantedModelDto);
}
