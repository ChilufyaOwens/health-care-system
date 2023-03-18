package com.ksi.healthcaresystem.registration.mapper;

import com.ksi.healthcaresystem.registration.dto.PatientDto;
import com.ksi.healthcaresystem.registration.dto.constants.Gender;
import com.ksi.healthcaresystem.registration.dto.constants.MaritalStatus;
import com.ksi.healthcaresystem.registration.entity.Patient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface PatientMapper {

  @Mappings({
      @Mapping(source = "gender", target = "gender", qualifiedByName = "stringToGenderEnum"),
      @Mapping(source = "dateOfBirth", target = "dateOfBirth", qualifiedByName = "stringToLocalDate"),
      @Mapping(source = "maritalStatus", target = "maritalStatus", qualifiedByName = "stringToMaritalStatusEnum")
  })
  Patient toEntity(PatientDto patientDto);

  PatientDto toDto(Patient patient);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Patient partialUpdate(
      PatientDto patientDto, @MappingTarget Patient patient);

  @Named("stringToGenderEnum")
  default Gender stringToGenderEnum(String genderType){
    return Arrays.stream(Gender.values())
        .filter(gender -> gender.getGender().equals(genderType))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);

  }

  @Named("stringToLocalDate")
  default LocalDate stringToLocalDate(String stringDate){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return LocalDate.parse(stringDate, formatter);
  }

  @Named("stringToMaritalStatusEnum")
  default MaritalStatus stringToMaritalStatusEnum(String status){
    return Arrays.stream(MaritalStatus.values())
        .filter(maritalStatus -> maritalStatus.getStatusCode().equals(status))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}