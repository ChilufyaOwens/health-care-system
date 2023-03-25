package com.ksi.healthcaresystem.registration.service.impl;

import com.ksi.healthcaresystem.registration.dto.AddressDto;
import com.ksi.healthcaresystem.registration.entity.Address;
import com.ksi.healthcaresystem.registration.entity.Patient;
import com.ksi.healthcaresystem.registration.mapper.AddressMapper;
import com.ksi.healthcaresystem.registration.repository.AddressRepository;
import com.ksi.healthcaresystem.registration.service.PatientAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2(topic = "PATIENT_ADDRESS_SERVICE")
public class PatientAddressServiceImpl implements PatientAddressService {

  private final AddressRepository addressRepository;
  private final AddressMapper addressMapper;
  /**
   * This method saves the address of the new registered patient
   * @param addressDto patient address details
   * @param patient registered patient details
   * @return Patient address details
   */
  @Override
  public AddressDto savePatientAddress(AddressDto addressDto, Patient patient) {
    log.info("Saving registered patient address with id: {} and address details : {}", patient.getId(), addressDto);
    Address address = addressMapper.toEntity(addressDto);
    address.setPatient(patient);
    Address savedAddress = addressRepository.save(address);
    return addressMapper.toDto(savedAddress);
  }
}
