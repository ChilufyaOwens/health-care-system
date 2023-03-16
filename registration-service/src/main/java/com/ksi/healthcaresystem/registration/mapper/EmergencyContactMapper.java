package com.ksi.healthcaresystem.registration.mapper;

import com.ksi.healthcaresystem.registration.dto.EmergencyContactDto;
import com.ksi.healthcaresystem.registration.entity.EmergencyContact;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface EmergencyContactMapper {

  EmergencyContact toEntity(EmergencyContactDto emergencyContactDto);

  EmergencyContactDto toDto(EmergencyContact emergencyContact);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  EmergencyContact partialUpdate(
      EmergencyContactDto emergencyContactDto, @MappingTarget EmergencyContact emergencyContact);
}