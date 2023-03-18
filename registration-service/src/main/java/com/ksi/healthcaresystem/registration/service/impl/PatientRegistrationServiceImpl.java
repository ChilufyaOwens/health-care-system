package com.ksi.healthcaresystem.registration.service.impl;

import com.ksi.healthcaresystem.registration.dto.PatientDto;
import com.ksi.healthcaresystem.registration.service.PatientRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PATIENT_REGISTRATION_SERVICE")
public class PatientRegistrationServiceImpl implements PatientRegistrationService {

  /**
   * This method registers new patient
   * @param patientDto patient registration details
   * @return registered patient
   */
  @Override
  public PatientDto registerPatient(PatientDto patientDto) {
    log.info("Registering new patient with details: {}", patientDto);
    return null;
  }
}
