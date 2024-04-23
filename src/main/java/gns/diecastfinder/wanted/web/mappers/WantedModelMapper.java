package gns.diecastfinder.wanted.web.mappers;

import gns.diecastfinder.wanted.domain.WantedModel;
import gns.diecastfinder.wanted.web.model.WantedModelDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WantedModelMapper {

    WantedModelDto wantedModelToWantedModelDto(WantedModel wantedModel);

    WantedModel wantedModelDtoToWantedModel(WantedModelDto wantedModelDto);
}
