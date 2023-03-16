package com.ksi.healthcaresystem.registration.dto;

import com.ksi.healthcaresystem.registration.dto.constants.Gender;
import com.ksi.healthcaresystem.registration.dto.constants.MaritalStatus;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A DTO for the {@link com.ksi.healthcaresystem.registration.entity.Patient} entity
 */
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@ToString
public class PatientDto implements Serializable {

  private final LocalDateTime createdAt;
  private final Long id;
  private final String healthCareNumber;
  private final String firstName;
  private final String otherName;
  private final String lastName;
  private final LocalDate dateOfBirth;
  private final String identificationNumber;
  private final String gender;
  private final String contactNumber;
  private final String email;
  private final String maritalStatus;
}