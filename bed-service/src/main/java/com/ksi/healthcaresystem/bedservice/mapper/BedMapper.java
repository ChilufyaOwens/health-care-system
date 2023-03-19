package com.ksi.healthcaresystem.bedservice.mapper;

import com.ksi.healthcaresystem.bedservice.dto.BedDto;
import com.ksi.healthcaresystem.bedservice.dto.constants.BedType;
import com.ksi.healthcaresystem.bedservice.entity.Bed;
import java.util.Arrays;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BedMapper {
  BedMapper MAPPER = Mappers.getMapper(BedMapper.class);

  @Mappings({
      @Mapping(source = "bedType", target = "bedType"),
      @Mapping(source = "status", target = "status")
  })
  BedDto mapToBedDto(Bed bed);

  @Mapping(source= "bedType", target = "bedType", qualifiedByName = "stringToEnum")
  Bed mapToBed(BedDto bedDto);

  @Named("stringToEnum")
  default BedType stringToEnum(String bedType){
    return Arrays.stream(BedType.values())
        .filter(bed -> bed.getName().equals(bedType))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }




}
