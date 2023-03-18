package com.ksi.healthcaresystem.registration.dto;

import com.ksi.healthcaresystem.registration.dto.AddressDto;
import com.ksi.healthcaresystem.registration.dto.EmergencyContactDto;
import com.ksi.healthcaresystem.registration.dto.InsuranceDto;
import com.ksi.healthcaresystem.registration.dto.constants.Gender;
import com.ksi.healthcaresystem.registration.dto.constants.MaritalStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import lombok.Data;

/**
 * A DTO for the {@link com.ksi.healthcaresystem.registration.entity.Patient} entity
 */
@Data
public class PatientDto implements Serializable {

  private final Long id;
  private final String healthCareNumber;
  @NotEmpty(message = "Firstname should not be empty or null")
  private final String firstName;
  private final String otherName;
  @NotEmpty(message = "Lastname should not be empty or null")
  private final String lastName;
  @NotEmpty(message = "Date of birth should not be empty or null")
  private final String dateOfBirth;
  private final String identificationNumber;
  @NotEmpty(message = "Gender should not be empty or null")
  private final String gender;
  private final String contactNumber;
  @Email
  private final String email;
  private final String maritalStatus;
  private final AddressDto address;
  private final Set<EmergencyContactDto> emergencyContacts;
  private final Set<InsuranceDto> insurances;
}