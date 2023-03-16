package com.ksi.healthcaresystem.registration.dto;

import com.ksi.healthcaresystem.registration.dto.PatientDto;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * A DTO for the {@link com.ksi.healthcaresystem.registration.entity.EmergencyContact} entity
 */
@Data
public class EmergencyContactDto implements Serializable {

  private final LocalDateTime createdAt;
  private final Long id;
  private final PatientDto patient;
  private final String firstName;
  private final String lastName;
  private final String otherName;
  private final String relationship;
  private final String contactNumber;
  private final String familyDocFirstName;
  private final String familyDocLastName;
  private final String familyDocContactNumber;
  private final String preferredPharmacy;
  private final String pharmacyContactNumber;
}