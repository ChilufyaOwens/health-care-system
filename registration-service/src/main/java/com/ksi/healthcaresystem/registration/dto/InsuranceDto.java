package com.ksi.healthcaresystem.registration.dto;

import com.ksi.healthcaresystem.registration.dto.PatientDto;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * A DTO for the {@link com.ksi.healthcaresystem.registration.entity.Insurance} entity
 */
@Data
public class InsuranceDto implements Serializable {

  private final LocalDateTime createdAt;
  private final Long id;
  private final PatientDto patient;
  private final String insuranceCompany;
  private final String insuranceNumber;
  private final String policyHolderFirstName;
  private final String policyHolderLastName;
  private final LocalDate dateOfBirth;
}