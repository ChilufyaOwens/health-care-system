package com.ksi.healthcaresystem.bedservice.mapper;

import com.ksi.healthcaresystem.bedservice.dto.BedDto;
import com.ksi.healthcaresystem.bedservice.entity.Bed;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BedMapper {
  BedMapper MAPPER = Mappers.getMapper(BedMapper.class);

  @Mappings({
      @Mapping(source = "bedType", target = "bedType"),
      @Mapping(source = "status", target = "status")
  })
  BedDto mapToBedDto(Bed bed);

  @Mappings({@Mapping(source = "bedType", target = "bedType")})
  Bed mapToBed(BedDto bedDto);

}
