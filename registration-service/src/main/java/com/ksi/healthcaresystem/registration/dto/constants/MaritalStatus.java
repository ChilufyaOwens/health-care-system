package com.ksi.healthcaresystem.registration.dto.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MaritalStatus {
  SINGLE("Single"),
  MARRIED("Married"),
  SEPARATED("Seperated"),
  DIVORCED("Divorced"),
  WIDOWED("Widowed");
  private String statusCode;
}
