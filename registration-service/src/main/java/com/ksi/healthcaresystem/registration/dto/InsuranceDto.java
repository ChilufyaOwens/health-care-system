package com.ksi.healthcaresystem.registration.dto;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A DTO for the {@link com.ksi.healthcaresystem.registration.entity.Insurance} entity*/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InsuranceDto implements Serializable {
  private Long id;
  private String insuranceCompany;
  private String insuranceNumber;
  private String policyHolderFirstName;
  private String policyHolderLastName;
  private LocalDate dateOfBirth;
}