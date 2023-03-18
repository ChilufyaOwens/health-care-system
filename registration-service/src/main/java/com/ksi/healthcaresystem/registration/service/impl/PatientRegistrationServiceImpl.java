package com.ksi.healthcaresystem.registration.service.impl;

import com.ksi.healthcaresystem.registration.dto.AddressDto;
import com.ksi.healthcaresystem.registration.dto.EmergencyContactDto;
import com.ksi.healthcaresystem.registration.dto.InsuranceDto;
import com.ksi.healthcaresystem.registration.dto.PatientDto;
import com.ksi.healthcaresystem.registration.entity.Patient;
import com.ksi.healthcaresystem.registration.mapper.PatientMapper;
import com.ksi.healthcaresystem.registration.repository.PatientRepository;
import com.ksi.healthcaresystem.registration.service.EmergencyContactService;
import com.ksi.healthcaresystem.registration.service.PatientAddressService;
import com.ksi.healthcaresystem.registration.service.PatientInsuranceService;
import com.ksi.healthcaresystem.registration.service.PatientRegistrationService;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PATIENT_REGISTRATION_SERVICE")
public class PatientRegistrationServiceImpl implements PatientRegistrationService {
  private final PatientRepository patientRepository;
  private final PatientMapper patientMapper;
  private final PatientAddressService patientAddressService;
  private final EmergencyContactService emergencyContactService;
  private final PatientInsuranceService patientInsuranceService;

  /**
   * This method registers new patient
   * @param patientDto patient registration details
   * @return registered patient
   */
  @Override
  public PatientDto registerPatient(PatientDto patientDto) {
    log.info("Registering new patient with details: {}", patientDto);
    Patient patient = patientMapper.toEntity(patientDto);
    Patient registeredPatient = patientRepository.save(patient);
    PatientDto savedPatient = patientMapper.toDto(registeredPatient);
    //Save patient address
    AddressDto savedAddress = patientAddressService.savePatientAddress(
        patientDto.getAddress(),
        registeredPatient);
    savedPatient.setAddress(savedAddress);
    //Save patient emergency contacts
    List<EmergencyContactDto> emergencyContacts = emergencyContactService.saveEmergencyContacts(
        patientDto.getEmergencyContacts(), registeredPatient);
    savedPatient.setEmergencyContacts(new HashSet<>(emergencyContacts));
    if(!patientDto.getInsurances().isEmpty()){
      List<InsuranceDto> insurances = patientInsuranceService.savePatientInsurances(patientDto
          .getInsurances()
          .stream()
          .toList(), registeredPatient);
      savedPatient.setInsurances(new HashSet<>(insurances));
    }
    return savedPatient;
  }
}
