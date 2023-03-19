package com.ksi.healthcaresystem.registration.service;


import com.ksi.healthcaresystem.registration.dto.PatientDto;

public interface PatientRegistrationService {
  PatientDto registerPatient(PatientDto patientDto);
}
