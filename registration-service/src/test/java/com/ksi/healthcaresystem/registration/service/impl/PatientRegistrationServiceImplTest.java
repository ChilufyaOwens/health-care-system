package com.ksi.healthcaresystem.registration.service.impl;


import static org.assertj.core.api.Assertions.assertThat;

import com.ksi.healthcaresystem.registration.dto.AddressDto;
import com.ksi.healthcaresystem.registration.dto.PatientDto;
import com.ksi.healthcaresystem.registration.dto.constants.Gender;
import com.ksi.healthcaresystem.registration.dto.constants.MaritalStatus;
import com.ksi.healthcaresystem.registration.entity.Address;
import com.ksi.healthcaresystem.registration.entity.Patient;
import com.ksi.healthcaresystem.registration.mapper.PatientMapper;
import com.ksi.healthcaresystem.registration.repository.PatientRepository;
import com.ksi.healthcaresystem.registration.service.EmergencyContactService;
import com.ksi.healthcaresystem.registration.service.HealCareNumberGeneratorService;
import com.ksi.healthcaresystem.registration.service.PatientAddressService;
import com.ksi.healthcaresystem.registration.service.PatientInsuranceService;
import com.ksi.healthcaresystem.registration.service.utils.EmailValidator;
import java.time.LocalDate;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PatientRegistrationServiceImplTest {

  @Mock
  PatientRepository patientRepository;
  @Mock
  PatientMapper patientMapper;
  @Mock
  PatientAddressService patientAddressService;
  @Mock
  EmergencyContactService emergencyContactService;
  @Mock
  PatientInsuranceService patientInsuranceService;
  @Mock
  HealCareNumberGeneratorService healCareNumberGeneratorService;

  @Test
  @DisplayName("Test should pass when a patient is registered")
  void shouldRegisterPatient() {
  }

  @Test
  @DisplayName("Test should throw an invalid email exception when email is not valid")
  void shouldFailToRegisterPatientWhenEmailIsInvalid() {
    assertThat(EmailValidator.isValidEmail("chilufya.kenzie@zra.com")).isTrue();
  }

  @Test
  @DisplayName("Should find registered patient by ID")
  void shouldFindRegisteredPatientById() {
    PatientRegistrationServiceImpl patientRegistrationService = new PatientRegistrationServiceImpl(
        patientRepository,
        patientMapper,
        patientAddressService,
        emergencyContactService,
        patientInsuranceService,
        healCareNumberGeneratorService
    );
    Patient patient = Patient.builder()
        .healthCareNumber("1223234232")
        .contactNumber("0963381206")
        .firstName("Chilufya")
        .gender(Gender.MALE)
        .identificationNumber("293267/66/1")
        .lastName("Kenzie")
        .maritalStatus(MaritalStatus.MARRIED)
        .email("chilufyaowens@gmail.com")
        .otherName("Owens")
        .dateOfBirth(LocalDate.of(2020, 10, 23))
        .address(
            Address.builder()
                .city("Lusaka")
                .streetAddressLineOne("Plot no. 83 Foxdale")
                .stateProvince("Lusaka")
                .country("Zambia")
                .build())
        .build();
    Mockito.when(patientRepository.findById(12L)).thenReturn(Optional.of(
        patient));
    PatientDto expectedPatientResponse = PatientDto.builder()
        .firstName("Chilufya")
        .lastName("Kenzie")
        .otherName("Owens")
        .email("chilufyaowens@gmail.com")
        .contactNumber("0963381206")
        .maritalStatus(MaritalStatus.DIVORCED.getStatusCode())
        .gender(Gender.MALE.getGender())
        .identificationNumber("293267/66/1")
        .dateOfBirth("01/01/2020")
        .address(AddressDto.builder()
            .city("Lusaka")
            .country("Zambia")
            .streetAddressLineOne("Plot no. 83 Foxdale")
            .country("Zambia")
            .stateProvince("Lusaka")
            .id(1L)
            .build())
        .build();
    Mockito.when(patientMapper.toDto(Mockito.any(Patient.class))).thenReturn(
        expectedPatientResponse
    );

    PatientDto actualPatientResponse = patientRegistrationService.getRegisteredPatientById(12L);
    Assertions.assertThat(actualPatientResponse.getId()).isEqualTo(expectedPatientResponse.getId());
    Assertions.assertThat(actualPatientResponse.getFirstName()).isEqualTo(expectedPatientResponse.getFirstName());
  }
}