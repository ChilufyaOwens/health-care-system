package com.ksi.healthcaresystem.registration.dto;

import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * A DTO for the {@link com.ksi.healthcaresystem.registration.entity.EmergencyContact} entity
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmergencyContactDto implements Serializable{
  private Long id;
  @NotEmpty(message = "Emergency contact firstname should not be empty or null")
  private String firstName;
  @NotEmpty(message = "Emergency contact lastname should not be empty or null")
  private String lastName;
  private String otherName;
  @NotEmpty(message = "Emergency contact relationship should not be empty or null")
  private String relationship;
  @NotEmpty(message = "Emergency contact contact number should not be empty or null")
  private String contactNumber;
  private String familyDocFirstName;
  private String familyDocLastName;
  private String familyDocContactNumber;
  private String preferredPharmacy;
  private String pharmacyContactNumber;
}