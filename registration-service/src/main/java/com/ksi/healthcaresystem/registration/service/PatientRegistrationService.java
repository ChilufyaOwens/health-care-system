package com.ksi.healthcaresystem.registration.service;


import com.ksi.healthcaresystem.registration.dto.PatientDto;
import java.util.List;

public interface PatientRegistrationService {
  PatientDto registerPatient(PatientDto patientDto);
  List<PatientDto> getAllRegisteredPatients();
  PatientDto getRegisteredPatientById(Long patientId);
  void deleteRegisteredPatient(Long patientId);
}
