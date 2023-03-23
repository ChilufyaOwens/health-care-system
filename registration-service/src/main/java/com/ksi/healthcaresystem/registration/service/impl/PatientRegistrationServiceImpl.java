package com.ksi.healthcaresystem.registration.service.impl;

import com.ksi.healthcaresystem.commons.exception.ResourceNotFoundException;
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
import com.ksi.healthcaresystem.registration.service.utils.HealthCareNumberGenerator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2(topic = "PATIENT_REGISTRATION_SERVICE")
public class PatientRegistrationServiceImpl implements PatientRegistrationService {

  private final PatientRepository patientRepository;
  private final PatientMapper patientMapper;
  private final PatientAddressService patientAddressService;
  private final EmergencyContactService emergencyContactService;
  private final PatientInsuranceService patientInsuranceService;

  /**
   * This method registers new patient
   *
   * @param patientDto patient registration details
   * @return registered patient
   */
  @Override
  public PatientDto registerPatient(PatientDto patientDto) {
    log.info("Registering new patient with details: {}", patientDto);
    Patient mappedPatient = patientMapper.toEntity(patientDto);
    //Create a patient object
    Patient patient = getPatient(mappedPatient);

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

    //Save only if patient has insurance
    if (!patientDto.getInsurances().isEmpty()) {
      List<InsuranceDto> insurances = patientInsuranceService.savePatientInsurances(patientDto
          .getInsurances()
          .stream()
          .toList(), registeredPatient);
      savedPatient.setInsurances(new HashSet<>(insurances));
    }
    return savedPatient;
  }

  /**
   * This method builds the patient object to be saved
   * @param mappedPatient mapped patient details
   * @return new patient
   */
  private static Patient getPatient(Patient mappedPatient) {
    //Get health care number
    String healthCareNumber = HealthCareNumberGenerator.generateHealthCareNumber();
    return new Patient(
        mappedPatient.getId(),
        healthCareNumber,
        mappedPatient.getFirstName(),
        mappedPatient.getOtherName(),
        mappedPatient.getLastName(),
        mappedPatient.getDateOfBirth(),
        mappedPatient.getIdentificationNumber(),
        mappedPatient.getGender(),
        mappedPatient.getContactNumber(),
        mappedPatient.getEmail(),
        mappedPatient.getMaritalStatus()
    );
  }

  /**
   * This method gets all registered patients
   *
   * @return list of all registered patients
   */
  @Override
  public List<PatientDto> getAllRegisteredPatients() {
    List<Patient> patients = patientRepository.findAll();
    List<PatientDto> patientList = new ArrayList<>();
    patients.forEach(patient -> {
      PatientDto patientDto = patientMapper.toDto(patient);
      patientList.add(patientDto);
    });

    return patientList;
  }

  /**
   * This method gets registered pstient by patient ID
   * @param patientId ID of the patient
   * @return patient dto if present
   */
  @Override
  public PatientDto getRegisteredPatientById(Long patientId) {
    log.info("Fetching registered patient with a given patient ID: {}", patientId);
    //Check if patient is found else throw resource not found exception
    Optional<Patient> optionalPatient = patientRepository.findById(patientId);
    if(optionalPatient.isEmpty()){
      throw new ResourceNotFoundException("Registered Patient", "id", String.valueOf(patientId));
    }
    return patientMapper.toDto(optionalPatient.get());
  }
}
