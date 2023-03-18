package com.ksi.healthcaresystem.registration.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link com.ksi.healthcaresystem.registration.entity.EmergencyContact} entity
 */
@Data
public class EmergencyContactDto implements Serializable {

  private final Long id;
  @NotNull(message = "Patient details should not be null")
  private final PatientDto patient;

  @NotEmpty(message = "Emergency contact firstname should not be empty or null")
  private final String firstName;
  @NotEmpty(message = "Emergency contact lastname should not be empty or null")
  private final String lastName;
  private final String otherName;
  @NotEmpty(message = "Emergency contact relationship should not be empty or null")
  private final String relationship;

  @NotEmpty(message = "Emergency contact contact number should not be empty or null")
  private final String contactNumber;
  private final String familyDocFirstName;
  private final String familyDocLastName;
  private final String familyDocContactNumber;
  private final String preferredPharmacy;
  private final String pharmacyContactNumber;
}