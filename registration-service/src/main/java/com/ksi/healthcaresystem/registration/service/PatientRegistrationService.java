package com.ksi.healthcaresystem.registration.service;


import com.ksi.healthcaresystem.commons.dto.ApiResponse;
import com.ksi.healthcaresystem.registration.dto.PatientDto;
import java.util.List;

public interface PatientRegistrationService {
  ApiResponse<PatientDto> registerPatient(PatientDto patientDto);
  ApiResponse<List<PatientDto>> getAllRegisteredPatients(Integer page, Integer size);
  ApiResponse<PatientDto> getRegisteredPatientById(Long patientId);
  void deleteRegisteredPatient(Long patientId);
}
