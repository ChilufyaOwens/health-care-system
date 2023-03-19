package com.ksi.healthcaresystem.registration.service;

import com.ksi.healthcaresystem.registration.dto.EmergencyContactDto;
import com.ksi.healthcaresystem.registration.entity.Patient;
import java.util.List;
import java.util.Set;

public interface EmergencyContactService {
  List<EmergencyContactDto> saveEmergencyContacts(Set<EmergencyContactDto> emergencyContactDtoList, Patient patient);
}
