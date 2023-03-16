package com.ksi.healthcaresystem.registration.mapper;

import com.ksi.healthcaresystem.registration.dto.PatientDto;
import com.ksi.healthcaresystem.registration.entity.Patient;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface PatientMapper {

  Patient toEntity(PatientDto patientDto);

  PatientDto toDto(Patient patient);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Patient partialUpdate(
      PatientDto patientDto, @MappingTarget Patient patient);
}