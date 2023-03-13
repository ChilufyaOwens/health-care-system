package com.ksi.healthcaresystem.bedservice.mapper;

import com.ksi.healthcaresystem.bedservice.dto.BedDto;
import com.ksi.healthcaresystem.bedservice.entity.Bed;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BedMapper {
  BedMapper MAPPER = Mappers.getMapper(BedMapper.class);

  BedDto mapToBedDto(Bed bed);
  Bed mapToBed(BedDto bedDto);

}
