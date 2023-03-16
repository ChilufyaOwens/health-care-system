package com.ksi.healthcaresystem.registration.mapper;

import com.ksi.healthcaresystem.registration.dto.InsuranceDto;
import com.ksi.healthcaresystem.registration.entity.Insurance;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface InsuranceMapper {

  Insurance toEntity(InsuranceDto insuranceDto);

  InsuranceDto toDto(Insurance insurance);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Insurance partialUpdate(
      InsuranceDto insuranceDto, @MappingTarget Insurance insurance);
}