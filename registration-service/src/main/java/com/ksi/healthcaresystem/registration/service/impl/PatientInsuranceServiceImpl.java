package com.ksi.healthcaresystem.registration.service.impl;

import com.ksi.healthcaresystem.registration.dto.InsuranceDto;
import com.ksi.healthcaresystem.registration.entity.Insurance;
import com.ksi.healthcaresystem.registration.entity.Patient;
import com.ksi.healthcaresystem.registration.mapper.InsuranceMapper;
import com.ksi.healthcaresystem.registration.repository.InsuranceRepository;
import com.ksi.healthcaresystem.registration.service.PatientInsuranceService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2(topic = "PATIENT_INSURANCE_SERVICE")
public class PatientInsuranceServiceImpl implements PatientInsuranceService {
  private final InsuranceRepository insuranceRepository;
  private final InsuranceMapper insuranceMapper;

  /**
   * This method saves registered patient insurances
   * @param insuranceDtoList patient insurance details
   * @param patient registered patient
   * @return list of registered patient details
   */
  @Override
  public List<InsuranceDto> savePatientInsurances(List<InsuranceDto> insuranceDtoList, Patient patient) {
    log.info("Saving insurance details for registered patient with ID: {} and insurance details: [{}]", patient.getId(),
        insuranceDtoList);
    List<Insurance> insurances = new ArrayList<>();
    insuranceDtoList.forEach(insuranceDto -> {
      //Convert to entity
      Insurance insurance = insuranceMapper.toEntity(insuranceDto);
      insurance.setPatient(patient);
      insurances.add(insurance);
    });
    List<Insurance> savedInsurances = insuranceRepository.saveAll(insurances);
    //Map to insurance dto and return a list of saved patient insurances
    List<InsuranceDto> patientInsurances = new ArrayList<>();
    savedInsurances.forEach(insurance -> patientInsurances.add(insuranceMapper.toDto(insurance)));
    return patientInsurances;
  }

}
