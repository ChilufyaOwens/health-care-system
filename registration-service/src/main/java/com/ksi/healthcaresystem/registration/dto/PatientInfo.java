package com.ksi.healthcaresystem.registration.dto;

import com.ksi.healthcaresystem.registration.dto.constants.Gender;
import com.ksi.healthcaresystem.registration.dto.constants.MaritalStatus;
import java.time.LocalDate;

/**
 * A Projection for the {@link com.ksi.healthcaresystem.registration.entity.Patient} entity
 */
public interface PatientInfo {

  Long getId();

  String getHealthCareNumber();

  String getFirstName();

  String getOtherName();

  String getLastName();

  LocalDate getDateOfBirth();

  String getIdentificationNumber();

  Gender getGender();

  String getContactNumber();

  String getEmail();

  MaritalStatus getMaritalStatus();
}