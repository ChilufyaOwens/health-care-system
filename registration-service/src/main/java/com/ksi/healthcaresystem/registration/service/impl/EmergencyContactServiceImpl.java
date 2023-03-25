package com.ksi.healthcaresystem.registration.service.impl;

import com.ksi.healthcaresystem.registration.dto.EmergencyContactDto;
import com.ksi.healthcaresystem.registration.entity.EmergencyContact;
import com.ksi.healthcaresystem.registration.entity.Patient;
import com.ksi.healthcaresystem.registration.mapper.EmergencyContactMapper;
import com.ksi.healthcaresystem.registration.repository.EmergencyContactRepository;
import com.ksi.healthcaresystem.registration.service.EmergencyContactService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2(topic = "EMERGENCY_CONTACTS_SERVICE")
public class EmergencyContactServiceImpl implements EmergencyContactService {
  private final EmergencyContactRepository emergencyContactRepository;
  private final EmergencyContactMapper emergencyContactMapper;
  /**
   * This method save registered patient emergency contacts
   * @param emergencyContactDtoList list of patient emergency contact
   * @param patient registered patient details
   * @return list of saved emergency contacts
   */
  @Override
  public List<EmergencyContactDto> saveEmergencyContacts(Set<EmergencyContactDto> emergencyContactDtoList,
      Patient patient) {
    log.info("Saving emergency contacts for patient with ID: {} and contact details: [{}]", patient.getId(), emergencyContactDtoList);
    List<EmergencyContact> emergencyContacts = new ArrayList<>();
    emergencyContactDtoList.forEach(emergencyContactDto -> {
      EmergencyContact emergencyContact = emergencyContactMapper.toEntity(emergencyContactDto);
      emergencyContact.setPatient(patient);
      emergencyContacts.add(emergencyContact);
    });
    List<EmergencyContact> savedEmergencyContacts = emergencyContactRepository.saveAll(emergencyContacts);
    List<EmergencyContactDto> patientEmergencyContacts = new ArrayList<>();
    savedEmergencyContacts.forEach(emergencyContact -> patientEmergencyContacts.add(emergencyContactMapper.toDto(emergencyContact)));
    return patientEmergencyContacts;
  }
}
