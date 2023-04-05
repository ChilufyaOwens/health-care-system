package com.ksi.healthcaresystem.registration.service.impl;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.ksi.healthcaresystem.registration.dto.AddressDto;
import com.ksi.healthcaresystem.registration.dto.PatientDto;
import com.ksi.healthcaresystem.registration.entity.Patient;
import com.ksi.healthcaresystem.registration.mapper.PatientMapper;
import com.ksi.healthcaresystem.registration.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PatientRegistrationServiceImplTest {

  @Mock
  private PatientRepository patientRepository;

  @Mock
  private PatientRegistrationServiceImpl patientRegistrationService;

  @Spy
  @InjectMocks
  private PatientMapper patientMapper = Mappers.getMapper(PatientMapper.class);

  private PatientDto patientDto;
  private AddressDto addressDto;


  @BeforeEach
  public void setup(){

    //Set up address
    addressDto = AddressDto.builder()
        .streetAddressLineOne("Plot 83, Foxdale")
        .city("Lusaka")
        .build();

    patientDto = PatientDto.builder()
        .id(1L)
        .firstName("Chilufya")
        .lastName("Owens")
        .gender("Male")
        .dateOfBirth("01/10/2020")
        .contactNumber("0963381206")
        .healthCareNumber("132042023")
        .maritalStatus("Single")
        .build();
  }

  @Test
  @DisplayName("Test for registering a new patient")
  void givenPatientObject_whenRegisterPatient_thenReturnPatientObject(){
    //given - precondition test
    Patient patient = patientMapper.toEntity(patientDto);

    given(patientRepository.findPatientByHealthCareNumber(patientDto.getHealthCareNumber()))
        .willReturn(null);

    given(patientRepository.save(patient)).willReturn(patient);

    //when - action or behaviour that we are going to test
    Patient savedPatient = patientRepository.save(patient);

    //then - verify the output
    assertThat(savedPatient).isNotNull();

  }

  PatientRegistrationServiceImplTest() {
  }

  @Test
  @DisplayName("Test should when resource not found")
  void resourceNotFoundExceptionTest(){

  }
  @Test
  @DisplayName("Test should fail when email address is not valid")
  void emailAddressNotValidTest() {
    PatientDto patientDto = new PatientDto();
  }

  @Test
  @DisplayName("Test should fail when patient address is null")
  void addressNotNullTest(){
    AddressDto addressDto = new AddressDto();
  }
}