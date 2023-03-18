package com.ksi.healthcaresystem.registration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A DTO for the {@link com.ksi.healthcaresystem.registration.entity.Patient} entity
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientDto implements Serializable {
  private Long id;
  private String healthCareNumber;
  @NotEmpty(message = "Firstname should not be empty or null")
  private String firstName;
  private String otherName;
  @NotEmpty(message = "Lastname should not be empty or null")
  private String lastName;
  @NotNull(message = "Date of birth should not be empty or null")
  private String dateOfBirth;
  private String identificationNumber;
  @NotEmpty(message = "Gender should not be empty or null")
  private String gender;
  private String contactNumber;
  @Email
  private String email;
  private String maritalStatus;
  private AddressDto address;
  @NotEmpty(message = "At least one emergency contact should be provided")
  private Set<EmergencyContactDto> emergencyContacts;
  private Set<InsuranceDto> insurances;
}
