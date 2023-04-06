package com.ksi.healthcaresystem.registration.service.impl;

import com.ksi.healthcaresystem.registration.entity.PatientHealCareNumber;
import com.ksi.healthcaresystem.registration.repository.PatientHealCareNumberRepository;
import com.ksi.healthcaresystem.registration.service.HealCareNumberGeneratorService;
import com.ksi.healthcaresystem.registration.service.utils.PatientHealthCareNumberGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2(topic = "HEALTH_CARE_NUMBER_GENERAToR_SERVICE")
public class HealCareNumberGeneratorServiceImpl implements HealCareNumberGeneratorService {

  private final PatientHealCareNumberRepository patientHealCareNumberRepository;

  /**
   * method to check if a newly generated patient health care number already exists in the database. We keep generating
   * new patient health care numbers until we find one that is unique.
   *
   * @return unique patient health care number
   */
  @Override
  public String generatePatientHealthCareNumber() {
    log.info("Generating patient health care number");
    String healthCareNumber;
    do {
      healthCareNumber = PatientHealthCareNumberGenerator.generatePatientHealthCareNumber();
    } while (patientHealCareNumberRepository.findPatientHealCareNumberByHealthCareNumber(healthCareNumber).isPresent());

    //save patient health care number to the database
    patientHealCareNumberRepository.save(
        PatientHealCareNumber.builder()
            .healthCareNumber(healthCareNumber)
            .build()
    );
    return healthCareNumber;
  }
}
