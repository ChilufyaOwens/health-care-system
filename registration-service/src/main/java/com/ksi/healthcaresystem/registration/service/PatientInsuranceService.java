package com.ksi.healthcaresystem.registration.service;

import com.ksi.healthcaresystem.registration.dto.InsuranceDto;
import com.ksi.healthcaresystem.registration.entity.Patient;
import java.util.List;

public interface PatientInsuranceService {
  List<InsuranceDto> savePatientInsurances(List<InsuranceDto> insuranceDtoList, Patient patient);
}
