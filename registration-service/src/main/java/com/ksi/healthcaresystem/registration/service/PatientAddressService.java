package com.ksi.healthcaresystem.registration.service;

import com.ksi.healthcaresystem.registration.dto.AddressDto;
import com.ksi.healthcaresystem.registration.entity.Patient;

public interface PatientAddressService {
  AddressDto savePatientAddress(AddressDto addressDto, Patient patient);
}
